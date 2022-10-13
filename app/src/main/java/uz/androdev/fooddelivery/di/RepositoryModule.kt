package uz.androdev.fooddelivery.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.androdev.fooddelivery.data.repository.FoodRepository
import uz.androdev.fooddelivery.data.repository.impl.FoodRepositoryImpl
import javax.inject.Singleton

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 6:03 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class FoodRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindFoodRepository(impl: FoodRepositoryImpl): FoodRepository
}
