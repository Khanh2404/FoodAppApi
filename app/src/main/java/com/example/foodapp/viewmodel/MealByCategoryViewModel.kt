package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.modol.Meal
import com.example.foodapp.modol.meals
import com.example.foodapp.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MealByCategoryViewModel:ViewModel() {
    private var mealLiveData = MutableLiveData<List<Meal>>()
    fun getDataMealByCate(id: Int){
        RetrofitClient.api.getMealsByCate(id).enqueue(object : retrofit2.Callback<meals> {
            override fun onResponse(call: Call<meals>, response: Response<meals>) {
                response.body()?.let { it->
                    mealLiveData.postValue(it.meals)
                }
            }

            override fun onFailure(call: Call<meals>, t: Throwable) {

            }

        })
    }
    fun observerMealData(): MutableLiveData<List<Meal>> {
        return mealLiveData
    }
}