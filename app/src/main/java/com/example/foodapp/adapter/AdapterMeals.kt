package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ItemMealBinding
import com.example.foodapp.modol.Meal

class AdapterMeals(private val onMealLongClick: (Meal, View) -> Unit): RecyclerView.Adapter<AdapterMeals.MyViewHold>() {
    private var mealsList = ArrayList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHold {
        return MyViewHold(ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHold, position: Int) {
        val meal = mealsList[position]
        holder.binding.txtnameMeal.text = mealsList[position].strMeal
        Glide.with(holder.itemView).load(mealsList[position].strMealThumb).into(holder.binding.imgMeal)
        holder.itemView.setOnLongClickListener {
            onMealLongClick(meal, it)
            true
        }
    }

    fun setDataMeals(meals: ArrayList<Meal>) {
        this.mealsList = meals
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    class MyViewHold(var binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}
