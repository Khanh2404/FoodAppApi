package com.example.foodapp.retrofit
import com.example.foodapp.modol.categories
import retrofit2.Call
import retrofit2.http.GET

interface FoodApi {
    @GET("cate")
    fun getCategories(): Call<categories>
}