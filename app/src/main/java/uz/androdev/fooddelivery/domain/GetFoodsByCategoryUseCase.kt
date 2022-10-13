package uz.androdev.fooddelivery.domain

import uz.androdev.fooddelivery.model.Food
import javax.inject.Inject

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 3:17 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class GetFoodsByCategoryUseCase @Inject constructor() {
    suspend operator fun invoke(categoryId: Int): List<Food> {
        return listOf(
            Food(
                id = 1,
                image = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.foodandwine.com%2Frecipes%2Fclassic-cheese-pizza&psig=AOvVaw2I-lmNrZaq1YL_51MCru95&ust=1665742687374000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCMDgi5z93PoCFQAAAAAdAAAAABAE",
                title = "Pizza 1",
                description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here,",
                price = 15f
            ),
            Food(
                id = 2,
                image = "https://static.toiimg.com/photo/56933159.cms",
                title = "Pizza 2",
                description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here,",
                price = 15f
            )
        )
    }
}