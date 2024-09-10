package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.databinding.FragmentFavortieBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.viewmodel.FovoriteViewModel

import com.example.foodapp.viewmodel.FovortieViewModel

class FovortieFragment: Fragment() {
    private lateinit var binding: FragmentFovoriteBinding
    private lateinit var adapter: FovoriteAdapter
    private lateinit var fovoriteViewModel: FovoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFovoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fovortieViewModel = ViewModelProvider(requireActivity()).get(FovortieViewModel::class.java)
        fovortieViewModel.fovortieItems.observe(viewLifecycleOwner, { items ->
            adapter.setData(items)
        })
    }

    private fun setupRecyclerView() {
        adapter = FovoriteAdapter()
        binding.recyclerViewFovorite.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewFovorite.adapter = adapter
    }
}