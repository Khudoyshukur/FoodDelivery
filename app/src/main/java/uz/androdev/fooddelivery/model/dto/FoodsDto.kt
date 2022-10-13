package uz.androdev.fooddelivery.model.dto

import com.google.gson.annotations.SerializedName

data class FoodsDto(

    @field:SerializedName("meals")
    val foods: List<FoodDto>? = null
)

data class FoodDto(
    @field:SerializedName("strMealThumb")
    val strMealThumb: String? = null,

    @field:SerializedName("idMeal")
    val idMeal: String? = null,

    @field:SerializedName("strMeal")
    val strMeal: String? = null
)
