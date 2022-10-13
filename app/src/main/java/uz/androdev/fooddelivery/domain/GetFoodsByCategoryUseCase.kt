package uz.androdev.fooddelivery.domain

import uz.androdev.fooddelivery.data.repository.FoodRepository
import uz.androdev.fooddelivery.model.Category
import uz.androdev.fooddelivery.model.Food
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 3:17 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class GetFoodsByCategoryUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(category: Category): List<Food> {
        return foodRepository.getFoodsByCategory(category)
    }
}