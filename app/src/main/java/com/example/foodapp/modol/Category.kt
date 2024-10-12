package com.example.foodapp.modol
import com.google.gson.annotations.SerializedName
class Category(
   @SerializedName("category")
    val category: String,
    @SerializedName("categoryDescription")
    val categoryDescription: String,
    @SerializedName("categoryThumb")
    val categoryThumb:String,
    @SerializedName("id")
    val id:Int,
   @SerializedName("price")
    val price:Int
)