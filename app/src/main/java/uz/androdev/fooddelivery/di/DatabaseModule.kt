package uz.androdev.fooddelivery.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.androdev.fooddelivery.data.db.AppDatabase
import javax.inject.Singleton

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 5:50 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideFoodDao(
        appDatabase: AppDatabase
    ) = appDatabase.foodDao
}