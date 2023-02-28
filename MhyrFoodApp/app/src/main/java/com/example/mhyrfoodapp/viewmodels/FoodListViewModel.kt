package com.example.mhyrfoodapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mhyrfoodapp.data.models.home.ResponseCategoryList
import com.example.mhyrfoodapp.data.models.home.ResponseFoodsList
import com.example.mhyrfoodapp.data.repository.FoodsListRepository
import com.example.mhyrfoodapp.utils.MyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(private val repository: FoodsListRepository) :
    ViewModel() {

    val randomFoodLiveData = MutableLiveData<List<ResponseFoodsList.Meal>>()
    fun loadRandomFood() = viewModelScope.launch {
        repository.randomFoodData().collect {
            randomFoodLiveData.postValue(it.body()?.meals)
        }
    }

    val filtersList = MutableLiveData<MutableList<Char>>()
    fun loadFiltersList() = viewModelScope.launch(Dispatchers.IO) {
        val filter = listOf('A'..'Z').flatten().toMutableList()
        filtersList.postValue(filter)
    }

    val categoriesList = MutableLiveData<MyResponse<ResponseCategoryList>>()
    fun loadCategoriesList() = viewModelScope.launch {
        repository.categoriesData().collect {
            categoriesList.postValue(it)
        }
    }

    val foodsList = MutableLiveData<MyResponse<ResponseFoodsList>>()
    fun loadFoodsList(letter: String) = viewModelScope.launch {
        repository.foodsData(letter).collect {
            foodsList.postValue(it)
        }
    }

    fun loadFoodsListBySearch(letter: String) = viewModelScope.launch {
        repository.foodsBySearch(letter).collect {
            foodsList.postValue(it)
        }
    }


    fun loadFoodsListByCategory(category: String) = viewModelScope.launch {
        repository.foodsByCategory(category).collect {
            foodsList.postValue(it)
        }
    }


}