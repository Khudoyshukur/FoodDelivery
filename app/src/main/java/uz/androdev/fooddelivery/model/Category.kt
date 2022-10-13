package uz.androdev.fooddelivery.model

import androidx.recyclerview.widget.DiffUtil.ItemCallback

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 2:54 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

data class Category(
    val id: Int,
    val name: String
) {
    companion object {
        val DIFF_UTIL = object : ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }
        }
    }
}