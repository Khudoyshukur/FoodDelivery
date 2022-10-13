package uz.androdev.fooddelivery.ui.menu

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androdev.fooddelivery.R
import uz.androdev.fooddelivery.databinding.ItemCategoryBinding

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 2:57 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class CategoryAdapter(
    private val onCategoryClicked: (UiCategory) -> Unit
) : ListAdapter<UiCategory, CategoryAdapter.CategoryViewHolder>(UiCategory.DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val uiCategory = getItem(position) ?: return
        holder.bind(uiCategory, onCategoryClicked)
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(uiCategory: UiCategory, onCategoryClicked: (UiCategory) -> Unit) = with(binding) {
            fun getColor(@ColorRes colorId: Int): Int {
                return ResourcesCompat.getColor(root.resources, colorId, null)
            }

            tvCategory.text = uiCategory.category.name

            if (uiCategory.isSelected) {
                tvCategory.setBackgroundColor(getColor(R.color.color_primary_20))
                tvCategory.setTextColor(getColor(R.color.color_primary))
            } else {
                tvCategory.setBackgroundColor(Color.WHITE)
                tvCategory.setTextColor(getColor(R.color.unselected_text_color))
            }

            root.setOnClickListener {
                onCategoryClicked(uiCategory)
            }
        }
    }
}