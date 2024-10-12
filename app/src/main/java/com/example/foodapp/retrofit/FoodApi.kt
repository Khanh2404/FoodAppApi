package com.example.foodapp.retrofit
import com.example.foodapp.modol.categories
import com.example.foodapp.modol.meals
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("cate")
        fun getCategories(): Call<categories>

    @GET("meal")
        fun getMeals(): Call<meals>

    @GET("meal")
    fun getMealsByCate(
        @Query("id") id: Int
    ): Call<meals>
}