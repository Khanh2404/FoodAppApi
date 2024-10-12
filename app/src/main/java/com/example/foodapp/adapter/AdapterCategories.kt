package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ItemCategoryBinding
import com.example.foodapp.modol.Category


class AdapterCategories():RecyclerView.Adapter<AdapterCategories.MyViewHold>() {
    private var categoriesList = ArrayList<Category>()
    var itemClickCate: ((Category) ->Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHold {
        return MyViewHold(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: MyViewHold, position: Int) {
        holder.binding.txtnameFood.text=categoriesList[position].category
        Glide.with(holder.itemView).load(categoriesList[position].categoryThumb).into(holder.binding.imgCategory)
        holder.itemView.setOnClickListener {
            itemClickCate!!.invoke(categoriesList[position])
        }
    }

    fun setDataCate(categories: ArrayList<Category> ){
        this.categoriesList= categories
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return categoriesList.size
    }
    class MyViewHold(var binding:ItemCategoryBinding) :RecyclerView.ViewHolder(binding.root){
    }
}