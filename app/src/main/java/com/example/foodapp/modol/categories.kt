package com.example.foodapp.modol
import com.google.gson.annotations.SerializedName
import java.util.Locale.Category

class categories (
    @SerializedName("categories")
    val categories: List<Category>
)