package uz.androdev.fooddelivery.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 6:28 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@Entity(tableName = "foods")
data class FoodEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "thumb") val thumb: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "category_name") val categoryName: String
)