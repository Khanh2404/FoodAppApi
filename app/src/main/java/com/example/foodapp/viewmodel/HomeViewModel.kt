package com.example.foodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.modol.Category
import com.example.foodapp.modol.categories
import com.example.foodapp.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
    private val categorisLiveData = MutableLiveData<List<Category>>()
    fun getCategories(){
        RetrofitClient.api.getCategories().enqueue(object :Callback<categories>{
            override fun onResponse(call: Call<categories>, response: Response<categories>) {
                response.body()?.let { categories1 ->
                    categorisLiveData.postValue(categories1.categories)
                }
            }

            override fun onFailure(call: Call<categories>, t: Throwable) {
                Log.e("loggg",t.message.toString())
            }

        })
    }
    fun observerCategoriesLiveData():LiveData<List<Category>>{
        return categorisLiveData
    }
}