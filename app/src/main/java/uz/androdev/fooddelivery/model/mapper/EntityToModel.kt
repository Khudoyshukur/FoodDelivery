package uz.androdev.fooddelivery.model.mapper

import uz.androdev.fooddelivery.model.Category
import uz.androdev.fooddelivery.model.entity.CategoryEntity

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 6:07 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

fun CategoryEntity.toCategory() = Category(
    id = id,
    name = name
)