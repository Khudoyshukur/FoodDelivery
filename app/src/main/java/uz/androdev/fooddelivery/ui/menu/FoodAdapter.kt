package uz.androdev.fooddelivery.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.androdev.fooddelivery.R
import uz.androdev.fooddelivery.databinding.ItemFoodBinding
import uz.androdev.fooddelivery.model.Food

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 6:54 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class FoodAdapter : ListAdapter<Food, FoodAdapter.FoodViewHolder>(Food.DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFoodBinding.inflate(inflater, parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = getItem(position) ?: return
        holder.bind(food)
    }

    class FoodViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(food: Food) = with(binding) {
            Glide.with(root)
                .load(food.image)
                .into(imgFoodThumb)
            tvTitle.text = food.title
            tvDescription.text = food.description
            tvPrice.text = root.context.getString(R.string.food_price_format, food.price)
        }
    }
}