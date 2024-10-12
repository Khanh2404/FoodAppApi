package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.adapter.FovoriteAdapter
import com.example.foodapp.databinding.FragmentFavoriteBinding
import com.example.foodapp.viewmodel.FovoriteViewModel


class FovoriteFragment: Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter: FovoriteAdapter
    private lateinit var fovoriteViewModel: FovoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fovoriteViewModel = ViewModelProvider(requireActivity()).get(FovoriteViewModel::class.java)
        fovoriteViewModel.fovoriteItems.observe(viewLifecycleOwner, { items ->
            adapter.setData(items)
        })
    }

    private fun setupRecyclerView() {
        adapter = FovoriteAdapter()
        binding.recyclerViewfovorite.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewfovorite.adapter = adapter
    }
}