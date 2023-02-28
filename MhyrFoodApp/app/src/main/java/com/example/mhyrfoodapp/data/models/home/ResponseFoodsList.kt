package com.example.mhyrfoodapp.data.models.home


import com.google.gson.annotations.SerializedName

data class ResponseFoodsList(
    @SerializedName("meals")
    val meals: List<Meal>
) {
    data class Meal(
        @SerializedName("dateModified")
        val dateModified: Any, // null
        @SerializedName("idMeal")
        val idMeal: String, // 53014
        @SerializedName("strArea")
        val strArea: String, // Italian
        @SerializedName("strCategory")
        val strCategory: String, // Miscellaneous
        @SerializedName("strCreativeCommonsConfirmed")
        val strCreativeCommonsConfirmed: Any, // null
        @SerializedName("strDrinkAlternate")
        val strDrinkAlternate: Any, // null
        @SerializedName("strImageSource")
        val strImageSource: Any, // null
        @SerializedName("strIngredient1")
        val strIngredient1: String, // Water
        @SerializedName("strIngredient10")
        val strIngredient10: String, // Basil
        @SerializedName("strIngredient11")
        val strIngredient11: String, // Black Pepper
        @SerializedName("strIngredient12")
        val strIngredient12: String,
        @SerializedName("strIngredient13")
        val strIngredient13: String,
        @SerializedName("strIngredient14")
        val strIngredient14: String,
        @SerializedName("strIngredient15")
        val strIngredient15: String,
        @SerializedName("strIngredient16")
        val strIngredient16: String,
        @SerializedName("strIngredient17")
        val strIngredient17: String,
        @SerializedName("strIngredient18")
        val strIngredient18: String,
        @SerializedName("strIngredient19")
        val strIngredient19: String,
        @SerializedName("strIngredient2")
        val strIngredient2: String, // Sugar
        @SerializedName("strIngredient20")
        val strIngredient20: String,
        @SerializedName("strIngredient3")
        val strIngredient3: String, // Yeast
        @SerializedName("strIngredient4")
        val strIngredient4: String, // Plain Flour
        @SerializedName("strIngredient5")
        val strIngredient5: String, // Salt
        @SerializedName("strIngredient6")
        val strIngredient6: String, // Olive Oil
        @SerializedName("strIngredient7")
        val strIngredient7: String, // Passata
        @SerializedName("strIngredient8")
        val strIngredient8: String, // Mozzarella
        @SerializedName("strIngredient9")
        val strIngredient9: String, // Oregano
        @SerializedName("strInstructions")
        val strInstructions: String, // 1 Preheat the oven to 230°C.2 Add the sugar and crumble the fresh yeast into warm water.3 Allow the mixture to stand for 10 – 15 minutes in a warm place (we find a windowsill on a sunny day works best) until froth develops on the surface.4 Sift the flour and salt into a large mixing bowl, make a well in the middle and pour in the yeast mixture and olive oil.5 Lightly flour your hands, and slowly mix the ingredients together until they bind.6 Generously dust your surface with flour.7 Throw down the dough and begin kneading for 10 minutes until smooth, silky and soft.8 Place in a lightly oiled, non-stick baking tray (we use a round one, but any shape will do!)9 Spread the passata on top making sure you go to the edge.10 Evenly place the mozzarella (or other cheese) on top, season with the oregano and black pepper, then drizzle with a little olive oil.11 Cook in the oven for 10 – 12 minutes until the cheese slightly colours.12 When ready, place the basil leaf on top and tuck in!
        @SerializedName("strMeal")
        val strMeal: String, // Pizza Express Margherita
        @SerializedName("strMealThumb")
        val strMealThumb: String, // https://www.themealdb.com/images/media/meals/x0lk931587671540.jpg
        @SerializedName("strMeasure1")
        val strMeasure1: String, // 150ml
        @SerializedName("strMeasure10")
        val strMeasure10: String, // Leaves
        @SerializedName("strMeasure11")
        val strMeasure11: String, // Pinch
        @SerializedName("strMeasure12")
        val strMeasure12: String,
        @SerializedName("strMeasure13")
        val strMeasure13: String,
        @SerializedName("strMeasure14")
        val strMeasure14: String,
        @SerializedName("strMeasure15")
        val strMeasure15: String,
        @SerializedName("strMeasure16")
        val strMeasure16: String,
        @SerializedName("strMeasure17")
        val strMeasure17: String,
        @SerializedName("strMeasure18")
        val strMeasure18: String,
        @SerializedName("strMeasure19")
        val strMeasure19: String,
        @SerializedName("strMeasure2")
        val strMeasure2: String, // 1 tsp 
        @SerializedName("strMeasure20")
        val strMeasure20: String,
        @SerializedName("strMeasure3")
        val strMeasure3: String, // 15g
        @SerializedName("strMeasure4")
        val strMeasure4: String, // 225g
        @SerializedName("strMeasure5")
        val strMeasure5: String, // 1 1/2 tsp 
        @SerializedName("strMeasure6")
        val strMeasure6: String, // Drizzle
        @SerializedName("strMeasure7")
        val strMeasure7: String, // 80g
        @SerializedName("strMeasure8")
        val strMeasure8: String, // 70g
        @SerializedName("strMeasure9")
        val strMeasure9: String, // Peeled and Sliced
        @SerializedName("strSource")
        val strSource: String, // https://www.dailymail.co.uk/femail/food/article-8240361/Pizza-Express-release-secret-recipe-Margherita-Pizza-make-home.html
        @SerializedName("strTags")
        val strTags: Any, // null
        @SerializedName("strYoutube")
        val strYoutube: String // https://www.youtube.com/watch?v=Mt5lgUZRoUg
    )
}