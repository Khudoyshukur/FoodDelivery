package uz.androdev.fooddelivery.data.repository

import uz.androdev.fooddelivery.model.Category
import uz.androdev.fooddelivery.model.Food

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 6:01 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

interface FoodRepository {
    suspend fun getFoodCategories(): List<Category>
    suspend fun getFoodsByCategory(category: Category): List<Food>
}