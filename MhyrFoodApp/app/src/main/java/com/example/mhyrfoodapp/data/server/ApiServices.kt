package com.example.mhyrfoodapp.data.server

import com.example.mhyrfoodapp.data.models.home.ResponseCategoryList
import com.example.mhyrfoodapp.data.models.home.ResponseFoodsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("random.php")
    suspend fun randomFoodData(): Response<ResponseFoodsList>

    @GET("categories.php")
    suspend fun categoriesFoodData(): Response<ResponseCategoryList>

    @GET("search.php")
    suspend fun foodsWithLetterData(@Query("f") letter: String): Response<ResponseFoodsList>

    @GET("search.php")
    suspend fun foodsWithSearchData(@Query("s") letter: String): Response<ResponseFoodsList>

    @GET("filter.php")
    suspend fun foodsWithCategory(@Query("c") category: String): Response<ResponseFoodsList>
}