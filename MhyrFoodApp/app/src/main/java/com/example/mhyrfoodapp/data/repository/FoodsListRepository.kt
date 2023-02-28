package com.example.mhyrfoodapp.data.repository

import com.example.mhyrfoodapp.data.models.home.ResponseCategoryList
import com.example.mhyrfoodapp.data.models.home.ResponseFoodsList
import com.example.mhyrfoodapp.data.server.ApiServices
import com.example.mhyrfoodapp.utils.MyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class FoodsListRepository @Inject constructor(private val api: ApiServices) {

    suspend fun randomFoodData(): Flow<Response<ResponseFoodsList>> {
        return flow {
            emit(api.randomFoodData())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun categoriesData(): Flow<MyResponse<ResponseCategoryList>> {
        return flow {
            emit(MyResponse.loading())
            when (api.categoriesFoodData().code()) {
                in 200..202 -> {
                    emit(MyResponse.success(api.categoriesFoodData().body()))
                }
            }
        }
    }

    suspend fun foodsData(letter: String): Flow<MyResponse<ResponseFoodsList>> {
        return flow {
            emit(MyResponse.loading())
            when (api.foodsWithLetterData(letter).code()) {
                in 200..202 -> {
                    emit(MyResponse.success(api.foodsWithLetterData(letter).body()))
                }
            }
        }
    }

    suspend fun foodsBySearch(name: String): Flow<MyResponse<ResponseFoodsList>> {
        return flow {
            emit(MyResponse.loading())
            when (api.foodsWithSearchData(name).code()) {
                in 200..202 -> {
                    emit(MyResponse.success(api.foodsWithSearchData(name).body()))
                }
            }
        }
    }

    suspend fun foodsByCategory(category: String): Flow<MyResponse<ResponseFoodsList>> {
        return flow {
            emit(MyResponse.loading())
            when (api.foodsWithCategory(category).code()) {
                in 200..202 -> {
                    emit(MyResponse.success(api.foodsWithCategory(category).body()))
                }
            }
        }
    }

}