package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ItemMealBinding
import com.example.foodapp.databinding.ItemMealbycateBinding
import com.example.foodapp.modol.Meal

class MealByCateAdepter:RecyclerView.Adapter<MealByCateAdepter.MyViewHold>() {

    private var mealsList = ArrayList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHold {
        return MyViewHold(ItemMealbycateBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHold, position: Int) {
        val meal = mealsList[position]
        holder.binding.txtnamemealbycate.text = mealsList[position].strMeal
        holder.binding.txtpricemealbycate.text= mealsList[position].price+"$"
        Glide.with(holder.itemView).load(mealsList[position].strMealThumb).into(holder.binding.imgmealbycate)
    }

    fun setDataMeals(meals: ArrayList<Meal>) {
        this.mealsList = meals
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    class MyViewHold(var binding: ItemMealbycateBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}