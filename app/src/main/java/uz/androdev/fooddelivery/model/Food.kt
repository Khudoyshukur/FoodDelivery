package uz.androdev.fooddelivery.model

import androidx.recyclerview.widget.DiffUtil.ItemCallback

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
) {
    companion object {
        val DIFF_UTIL = object : ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem == newItem
            }
        }
    }
}