package uz.androdev.fooddelivery.data.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.androdev.fooddelivery.data.db.dao.FoodDao
import uz.androdev.fooddelivery.data.repository.FoodRepository
import uz.androdev.fooddelivery.data.service.FoodService
import uz.androdev.fooddelivery.model.Category
import uz.androdev.fooddelivery.model.Food
import uz.androdev.fooddelivery.model.mapper.toCategory
import uz.androdev.fooddelivery.model.mapper.toCategoryEntity
import uz.androdev.fooddelivery.model.mapper.toFood
import uz.androdev.fooddelivery.model.mapper.toFoodEntity
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 6:03 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class FoodRepositoryImpl @Inject constructor(
    private val foodService: FoodService,
    private val foodDao: FoodDao
) : FoodRepository {
    override suspend fun getFoodCategories(): List<Category> {
        try {
            val categories = foodService.getAllCategories().body()!!.categories!!
            val entities = categories.mapNotNull {
                withContext(Dispatchers.IO) { it.toCategoryEntity() }
            }
            foodDao.insertFoodCategories(entities)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return foodDao.getAllCategories().map {
            withContext(Dispatchers.IO) {
                it.toCategory()
            }
        }
    }

    override suspend fun getFoodsByCategory(category: Category): List<Food> {
        try {
            val remoteFoods = foodService.getFoodsByCategory(category.name).body()!!.foods!!
            val entities = remoteFoods.mapNotNull {
                withContext(Dispatchers.IO) {
                    it.toFoodEntity(category.name)
                }
            }
            foodDao.insertFoods(entities)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return foodDao.getAllFoods(category.name).map {
            withContext(Dispatchers.IO) {
                it.toFood()
            }
        }
    }
}