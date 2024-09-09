package com.example.foodapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.adapter.CartAdapter
import com.example.foodapp.databinding.FragmentCartBinding
import com.example.foodapp.viewmodel.CartViewModel

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartAdapter
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        cartViewModel.cartItems.observe(viewLifecycleOwner, { items ->
            Log.d("CartFragment", "Cart items updated: $items")
            adapter.setData(items)
        })
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter()
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCart.adapter = adapter
    }
}
