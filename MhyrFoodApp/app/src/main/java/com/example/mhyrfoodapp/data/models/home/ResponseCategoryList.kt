package com.example.mhyrfoodapp.data.models.home


import com.google.gson.annotations.SerializedName
import java.util.Locale.Category

data class ResponseCategoryList(
    @SerializedName("categories")
    val categories: List<Category>
) {
    data class Category(
        @SerializedName("idCategory")
        val idCategory: String, // 1
        @SerializedName("strCategory")
        val strCategory: String, // Beef
        @SerializedName("strCategoryDescription")
        val strCategoryDescription: String, // Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]
        @SerializedName("strCategoryThumb")
        val strCategoryThumb: String // https://www.themealdb.com/images/category/beef.png
    )
}