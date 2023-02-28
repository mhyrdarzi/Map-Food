package com.example.mhyrfoodapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.mhyrfoodapp.R
import com.example.mhyrfoodapp.databinding.FragmentHomeBinding
import com.example.mhyrfoodapp.ui.home.adapters.CategoriesAdapter
import com.example.mhyrfoodapp.ui.home.adapters.FoodsAdapter
import com.example.mhyrfoodapp.utils.*
import com.example.mhyrfoodapp.viewmodels.FoodListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //Binding
    private var _bind: FragmentHomeBinding? = null
    private val bind get() = _bind

    //ViewModel
    private val viewModel: FoodListViewModel by viewModels()

    //Adapter
    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var foodsAdapter: FoodsAdapter

    //Check connection
    @Inject
    lateinit var checkConnection: CheckConnection

    enum class PageStatus {
        NONE, DISCONNECTED, EMPTY
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentHomeBinding.inflate(layoutInflater)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Init views
        bind?.apply {
            //Call random food api
            viewModel.loadRandomFood()
            //Get random food data
            viewModel.randomFoodLiveData.observe(viewLifecycleOwner) {
                it[0].let { meal ->
                    imgFood.load(meal.strMealThumb) {
                        crossfade(true)
                        crossfade(500)
                    }
                }
            }
            //Filter spinner setting yp
            viewModel.loadFiltersList()
            viewModel.filtersList.observe(viewLifecycleOwner) {
                filterSpinner.setupListWithAdapter(it) { letters ->
                    viewModel.loadFoodsList(letters)
                }
            }
            //Category api calling
            viewModel.loadCategoriesList()
            viewModel.categoriesList.observe(viewLifecycleOwner) {
                when (it.status) {
                    MyResponse.Status.LOADING -> {
                        loaderCategory.isVisible(true, recyclerCategory)
                    }
                    MyResponse.Status.SUCCESS -> {
                        loaderCategory.isVisible(false, recyclerCategory)
                        categoriesAdapter.setData(it.data!!.categories)
                        recyclerCategory.setupRecyclerView(
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            ),
                            categoriesAdapter
                        )
                    }
                    MyResponse.Status.ERROR -> {
                        loaderCategory.isVisible(false, recyclerCategory)
                        Toast.makeText(context, "Categories loading failed!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            categoriesAdapter.setOnItemClickListener {
                viewModel.loadFoodsListByCategory(it.strCategory)
            }
            //Foods api calling
            viewModel.loadFoodsList("A")
            viewModel.foodsList.observe(viewLifecycleOwner) {
                when (it.status) {
                    MyResponse.Status.ERROR -> {
                        loaderFood.isVisible(false, recyclerRandomFood)
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                    MyResponse.Status.SUCCESS -> {
                        loaderFood.isVisible(false, recyclerRandomFood)
                        if(it.data!!.meals.isNotEmpty()) {
                            checkConnectionOrEmpty(false, PageStatus.NONE)
                            foodsAdapter.setData(it.data.meals)
                            recyclerRandomFood.setupRecyclerView(
                                LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                ),
                                foodsAdapter
                            )
                        } else {
                            checkConnectionOrEmpty(true, PageStatus.EMPTY)
                        }
                        foodsAdapter.setData(it.data.meals)
                        recyclerRandomFood.setupRecyclerView(
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            ),
                            foodsAdapter
                        )
                    }
                    MyResponse.Status.LOADING -> {
                        loaderFood.isVisible(true, recyclerRandomFood)
                    }
                }
            }
            //Setup search food
            edtFoodName.addTextChangedListener {
                if (it.toString().length > 2) {
                    viewModel.loadFoodsListBySearch(it.toString())
                }
            }
            //Check connection
            checkConnection.observe(viewLifecycleOwner) {
                if (it) {
                    checkConnectionOrEmpty(false, PageStatus.NONE)
                } else {
                    checkConnectionOrEmpty(true, PageStatus.DISCONNECTED)
                }
            }
        }
    }

    override fun onStop() {
        super.onDestroy()
        _bind = null
    }

    private fun checkConnectionOrEmpty(isShownError: Boolean, state: PageStatus) {
        bind?.apply {
            if (isShownError) {
                //Show disconnected/empty layout
                sectionDisconnected.isVisible(true, sectionAllContent)
                when (state) {
                    PageStatus.EMPTY -> {
                        imgDisconnectedOrEmpty.setImageResource(R.drawable.ic_baseline_inbox_24)
                        txtDisconnectedOrEmpty.text = "Sorry, its empty ):"

                    }
                    PageStatus.DISCONNECTED -> {
                        imgDisconnectedOrEmpty.setImageResource(R.drawable.disconnected)
                        txtDisconnectedOrEmpty.text = "Oops! check your connection"
                    }
                    else -> {}
                }
            } else {
                sectionAllContent.isVisible(true, sectionDisconnected)
            }
        }
    }
}