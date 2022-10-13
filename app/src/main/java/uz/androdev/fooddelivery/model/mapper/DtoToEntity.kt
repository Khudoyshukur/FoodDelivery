package uz.androdev.fooddelivery.model.mapper

import uz.androdev.fooddelivery.model.dto.CategoryDto
import uz.androdev.fooddelivery.model.entity.CategoryEntity

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