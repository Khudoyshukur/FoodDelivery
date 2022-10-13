package uz.androdev.fooddelivery.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.androdev.fooddelivery.model.entity.CategoryEntity
import uz.androdev.fooddelivery.model.entity.FoodEntity

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 5:56 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoods(foods: List<FoodEntity>)

    @Query("SELECT * FROM foods WHERE category_name=:categoryName")
    suspend fun getAllFoods(categoryName: String): List<FoodEntity>
}