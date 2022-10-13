package uz.androdev.fooddelivery.model.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 5:48 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

data class CategoriesDto(
	@field:SerializedName("categories")
	val categories: List<CategoryDto>? = null
)

data class CategoryDto(

	@field:SerializedName("strCategory")
	val strCategory: String? = null,

	@field:SerializedName("strCategoryDescription")
	val strCategoryDescription: String? = null,

	@field:SerializedName("idCategory")
	val idCategory: String? = null,

	@field:SerializedName("strCategoryThumb")
	val strCategoryThumb: String? = null
)
