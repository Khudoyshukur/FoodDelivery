package uz.androdev.fooddelivery.model.mapper

import uz.androdev.fooddelivery.model.dto.CategoryDto
import uz.androdev.fooddelivery.model.dto.FoodDto
import uz.androdev.fooddelivery.model.dto.FoodsDto
import uz.androdev.fooddelivery.model.entity.CategoryEntity
import uz.androdev.fooddelivery.model.entity.FoodEntity

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 6:07 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

fun CategoryDto.toCategoryEntity(): CategoryEntity? {
    return try {
        CategoryEntity(
            id = idCategory!!.toInt(),
            name = strCategory!!,
            description = strCategoryDescription!!,
            thumb = strCategoryThumb ?: ""
        )
    } catch (e: Exception) {
        null
    }
}

/**
 * Note that I am using description as Lorem Ipsum.
 * And the price is generated randomly within range of 1 to 20.
 *
 * The API that I am using didn't provide that fields, that's why
 * I decided to do that.
 * */

fun FoodDto.toFoodEntity(categoryName: String): FoodEntity? {
    val price = (1..20).random()
    val description = """
        Lorem Ipsum is simply dummy text of the printing 
        and typesetting industry. Lorem Ipsum has been the 
        industry's standard dummy text ever since the 1500s, 
        when an unknown printer took
    """.trimIndent()
    return try {
        FoodEntity(
            id = idMeal!!.toLong(),
            name = strMeal!!,
            thumb = strMealThumb ?: "",
            description = description,
            price = price,
            categoryName = categoryName
        )
    } catch (e: Exception) {
        null
    }
}