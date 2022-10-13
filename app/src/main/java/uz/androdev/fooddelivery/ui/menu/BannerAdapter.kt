package uz.androdev.fooddelivery.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androdev.fooddelivery.databinding.ItemBannerBinding
import uz.androdev.fooddelivery.ui.base.dpToPx
import kotlin.math.roundToInt

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 9:14 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class BannerAdapter : ListAdapter<Banner, BannerAdapter.BannerViewHolder>(Banner.DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBannerBinding.inflate(inflater, parent, false).also {
            val expectedWidth =
                parent.resources.displayMetrics.widthPixels - parent.resources.dpToPx(56f)
            val expectedHeight = expectedWidth * 122 / 300

            it.root.layoutParams = it.root.layoutParams.apply {
                height = expectedHeight.roundToInt()
                width = expectedWidth.roundToInt()
            }

//                ViewGroup.LayoutParams(
//                expectedWidth.roundToInt(),
//                expectedHeight.roundToInt()
//            )
        }
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = getItem(position) ?: return
        holder.bind(banner)
    }

    class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: Banner) {
            binding.root.setImageResource(banner.bannerId)
        }
    }
}

data class Banner(
    @DrawableRes val bannerId: Int
) {
    companion object {
        val DIFF_UTIL = object : ItemCallback<Banner>() {
            override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
                return oldItem.bannerId == newItem.bannerId
            }

            override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
                return oldItem == newItem
            }
        }
    }
}