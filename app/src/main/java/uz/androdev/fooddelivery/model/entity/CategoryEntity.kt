package uz.androdev.fooddelivery.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 5:53 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "thumb") val thumb: String
)