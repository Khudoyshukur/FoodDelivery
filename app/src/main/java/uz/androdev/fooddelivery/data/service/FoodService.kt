package uz.androdev.fooddelivery.data.service

import retrofit2.Response
import retrofit2.http.GET
import uz.androdev.fooddelivery.model.dto.CategoriesDto

/**
 * Created by: androdev
 * Date: 13-10-2022
 * Time: 5:46 PM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

interface FoodService {
    @GET("api/json/v1/1/categories.php")
    suspend fun getAllCategories(): Response<CategoriesDto>
}