package com.example.foodapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.adapter.MealByCateAdepter
import com.example.foodapp.databinding.ActivityMealByCateBinding
import com.example.foodapp.viewmodel.MealByCategoryViewModel


class MealByCateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealByCateBinding
    private lateinit var viewModel: MealByCategoryViewModel
    private lateinit var mealByCateAdepter: MealByCateAdepter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealByCateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtNameCate.text = intent.getStringExtra("Name")
        initRecycle()
        viewModel = ViewModelProvider(this).get(MealByCategoryViewModel::class.java)
        viewModel.getDataMealByCate(intent.getIntExtra("idcate", 0))
        viewModel.getDataMealByCate(intent.getIntExtra("price", 0))
        viewModel.observerMealData().observe(this, { mealList ->
            mealByCateAdepter.setDataMeals(mealList as ArrayList)
        })
    }

    private fun initRecycle() {
        mealByCateAdepter = MealByCateAdepter()
        binding.rcvItemCate.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = mealByCateAdepter
//            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
//                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
//                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
//
//                    for (i in firstVisibleItemPosition..lastVisibleItemPosition) {
//                        val view = layoutManager.findViewByPosition(i)
//                        view?.scaleX = 1.2f  // Tăng kích thước ngang
//                        view?.scaleY = 1.2f  // Tăng kích thước dọc
//                    }
//                }
//            })
        }
    }
}