package com.example.foodapp.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.modol.Meal

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var items: List<Meal> = emptyList()

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mealName: TextView = itemView.findViewById(R.id.mealName)
        private val mealImage: ImageView = itemView.findViewById(R.id.mealImage)

        fun bind(meal: Meal) {
            mealName.text = meal.strMeal
            Glide.with(itemView.context).load(meal.strMealThumb).into(mealImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setData(newItems: List<Meal>) {
        items = newItems
        notifyDataSetChanged()
    }
}
