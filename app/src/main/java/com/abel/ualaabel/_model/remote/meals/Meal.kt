package com.abel.ualaabel._model.remote.meals

import java.io.Serializable

data class Meal(
    val dateModified: Any,
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strDrinkAlternate: Any,
    val strIngredient1: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient2: String?,
    val strIngredient20: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strInstructions: String,
    val strMeal: String,
    val strMealThumb: String,
    val strMeasure1: String,
    val strMeasure10: String,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String,
    val strMeasure15: String,
    val strMeasure16: String,
    val strMeasure17: String,
    val strMeasure18: String,
    val strMeasure19: String,
    val strMeasure2: String,
    val strMeasure20: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strSource: String,
    val strTags: String,
    val strYoutube: String
): Serializable{

    fun generateListIngredientes():List<String>{
        val ingredientes = ArrayList<String>()

        if (!strIngredient1.isNullOrBlank()&& strIngredient1 != "null")
            ingredientes.add(strIngredient1)
        if (!strIngredient2.isNullOrBlank()&& strIngredient2 != "null")
            ingredientes.add(strIngredient2)
        if (!strIngredient3.isNullOrBlank()&& strIngredient3 != "null")
            ingredientes.add(strIngredient3)
        if (!strIngredient4.isNullOrBlank()&& strIngredient4 != "null")
            ingredientes.add(strIngredient4)
        if (!strIngredient5.isNullOrBlank()&& strIngredient5 != "null")
            ingredientes.add(strIngredient5)
        if (!strIngredient6.isNullOrBlank()&& strIngredient6 != "null")
            ingredientes.add(strIngredient6)
        if (!strIngredient7.isNullOrBlank()&& strIngredient7 != "null")
            ingredientes.add(strIngredient7)
        if (!strIngredient8.isNullOrBlank()&& strIngredient8 != "null")
            ingredientes.add(strIngredient8)
        if (!strIngredient9.isNullOrBlank()&& strIngredient9!= "null")
            ingredientes.add(strIngredient9)
        if (!strIngredient10.isNullOrBlank()&& strIngredient10 != "null")
            ingredientes.add(strIngredient10)
        if (!strIngredient11.isNullOrBlank()&& strIngredient11 != "null")
            ingredientes.add(strIngredient11)
        if (!strIngredient12.isNullOrBlank()&& strIngredient12 != "null")
            ingredientes.add(strIngredient12)
        if (!strIngredient13.isNullOrBlank()&& strIngredient13 != "null")
            ingredientes.add(strIngredient13)
        if (!strIngredient14.isNullOrBlank()&& strIngredient14 != "null")
            ingredientes.add(strIngredient14)
        if (!strIngredient15.isNullOrBlank()&& strIngredient15 != "null")
            ingredientes.add(strIngredient15)
        if (!strIngredient16.isNullOrBlank()&& strIngredient16 != "null")
            ingredientes.add(strIngredient16)
        if (!strIngredient17.isNullOrBlank()&& strIngredient17 != "null")
            ingredientes.add(strIngredient17)
        if (!strIngredient18.isNullOrBlank()&& strIngredient18 != "null")
            ingredientes.add(strIngredient18)
        if (!strIngredient19.isNullOrBlank()&& strIngredient19 != "null")
            ingredientes.add(strIngredient19)
        if (!strIngredient20.isNullOrBlank()&& strIngredient20 != "null")
            ingredientes.add(strIngredient20)

        return ingredientes

    }
}