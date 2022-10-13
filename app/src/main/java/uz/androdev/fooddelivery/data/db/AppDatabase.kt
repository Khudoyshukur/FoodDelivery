package uz.androdev.fooddelivery.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.androdev.fooddelivery.data.db.dao.FoodDao
import uz.androdev.fooddelivery.model.entity.CategoryEntity
import uz.androdev.fooddelivery.model.entity.FoodEntity

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 5:50 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */
@Database(
    entities = [
        CategoryEntity::class,
        FoodEntity::class
    ],
    exportSchema = true,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val foodDao: FoodDao

    companion object {
        private const val DATABASE_NAME = "app_database.db"

        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return instance!!
        }
    }
}