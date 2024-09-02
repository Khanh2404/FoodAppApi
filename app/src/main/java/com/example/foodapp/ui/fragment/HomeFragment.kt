package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapter.AdapterCategories
import com.example.foodapp.databinding.FragmentHomeBinding
import com.example.foodapp.modol.Category
import com.example.foodapp.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

   private lateinit var binding: FragmentHomeBinding
   private lateinit var viewModel: HomeViewModel
   private lateinit var adapterCategories: AdapterCategories
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getCategories()
        observerLiveData()
    }
    private fun initView(){
        adapterCategories= AdapterCategories()
        binding.recyview.apply {
            layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter=adapterCategories
        }
    }
    private fun observerLiveData(){
        viewModel.observerCategoriesLiveData().observe(viewLifecycleOwner,{categories->
                adapterCategories.setDataCate(categories as ArrayList<Category>)
        })
    }
}