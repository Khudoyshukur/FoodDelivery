package uz.androdev.fooddelivery.domain

import uz.androdev.fooddelivery.data.repository.FoodRepository
import uz.androdev.fooddelivery.model.Category
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 3:15 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class GetCategoriesUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(): List<Category> {
        return foodRepository.getFoodCategories()
    }
}