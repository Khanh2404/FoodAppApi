package com.example.foodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.modol.Meal

class FovoriteViewModel:ViewModel() {
    private val _fovoriteItems = MutableLiveData<ArrayList<Meal>>().apply { value = arrayListOf() }
    val fovoriteItems: LiveData<ArrayList<Meal>> = _fovoriteItems
    fun addItem(meal: Meal) {
        val currentList = _fovoriteItems.value ?: arrayListOf()
        currentList.add(meal)
        _fovoriteItems.value = currentList
        Log.d("CartViewModel", "Item added: $meal")
    }
}