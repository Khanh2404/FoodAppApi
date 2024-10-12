package com.example.foodapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.adapter.AdapterCategories
import com.example.foodapp.adapter.AdapterMeals
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.modol.Category
import com.example.foodapp.modol.Meal
import com.example.foodapp.modol.categories
import com.example.foodapp.modol.meals
import com.example.foodapp.ui.MainActivity
import com.example.foodapp.ui.MealByCateActivity
import com.example.foodapp.viewmodel.CartViewModel
import com.example.foodapp.viewmodel.FovoriteViewModel
import com.example.foodapp.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapterCategories: AdapterCategories
    private lateinit var adapterMeals: AdapterMeals
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getCategories()
        viewModel.getMeals()
        observerLiveData()
        onItemClickCategory()
        binding.btnHamburger.setOnClickListener {
            (activity as? MainActivity)?.openDrawer()
        }
    }

    private fun onItemClickCategory() {
        adapterCategories.itemClickCate={categories->
            var inten = Intent(activity, MealByCateActivity::class.java)
            inten.putExtra("idcate", categories.id)
            inten.putExtra("Name",categories.category)
            startActivity(inten)
        }
    }

    private fun initView() {
        adapterCategories = AdapterCategories()
        binding.recyview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterCategories
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                    for (i in firstVisibleItemPosition..lastVisibleItemPosition) {
                        val view = layoutManager.findViewByPosition(i)
                        view?.scaleX = 1.2f  // Tăng kích thước ngang
                        view?.scaleY = 1.2f  // Tăng kích thước dọc
                    }
                }
            })
        }
        adapterMeals = AdapterMeals { meal, view ->
            showPopupMenu(view, meal)
        }
        // Initialize the adapter for meals
        binding.recyviewMeals.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = adapterMeals
        }
    }

    private fun observerLiveData() {
        viewModel.observerCategoriesLiveData().observe(viewLifecycleOwner, { categories ->
            adapterCategories.setDataCate(categories as ArrayList<Category>)
        })
        viewModel.observeMealsLiveData().observe(viewLifecycleOwner, { meals ->
            adapterMeals.setDataMeals(meals as ArrayList<Meal>)
        })
    }

    private fun showPopupMenu(view: View, meal: Meal) {
        Log.d("HomeFragment", "Show popup menu for meal: $meal")
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.menuInflater.inflate(R.menu.menu_item_meal, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_add_to_cart -> {
                    if (meal.strMeal.isNotEmpty() && meal.strMealThumb.isNotEmpty()) {
                        val cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
                        cartViewModel.addItem(meal)

                        // Sử dụng NavController để điều hướng
                        val navController = requireActivity().findNavController(R.id.framentContainer)
                        navController.navigate(R.id.action_homeFragment_to_cartFragment)
                        true
                    } else {
                        Log.e("HomeFragment", "Invalid meal data: $meal")
                        false
                    }
                }
                R.id.action_add_to_favorites -> {
                    if (meal.strMeal.isNotEmpty() && meal.strMealThumb.isNotEmpty()) {
                        val fovortieViewModel = ViewModelProvider(requireActivity()).get(
                            FovoriteViewModel::class.java)
                        fovortieViewModel.addItem(meal)

                        // Sử dụng NavController để điều hướng
                        val navController = requireActivity().findNavController(R.id.framentContainer)
                        navController.navigate(R.id.action_homeFragment_to_fovoriteFragment)
                        true
                    } else {
                        Log.e("HomeFragment", "Invalid meal data: $meal")
                        false
                    }
                }
                else -> {
                    false
                }
            }
        }
        popupMenu.show()
    }
}
