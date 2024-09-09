package com.example.foodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.modol.Meal

class CartViewModel : ViewModel() {
    private val _cartItems = MutableLiveData<ArrayList<Meal>>().apply { value = arrayListOf() }
    val cartItems: LiveData<ArrayList<Meal>> = _cartItems
    fun addItem(meal: Meal) {
        val currentList = _cartItems.value ?: arrayListOf()
        currentList.add(meal)
        _cartItems.value = currentList
        Log.d("CartViewModel", "Item added: $meal")
    }
}

