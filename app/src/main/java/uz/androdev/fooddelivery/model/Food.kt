package uz.androdev.fooddelivery.model

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 3:14 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

data class Food(
    val id: Long,
    val image: String,
    val title: String,
    val description: String,
    val price: Int
)