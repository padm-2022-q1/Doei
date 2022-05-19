package com.example.doei.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doei.R
import com.example.doei.databinding.FragmentHomeBinding
import com.example.doei.domain.models.Product
import com.example.doei.domain.utils.KeyboardUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ProductAdapter.Listener {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initObservers()
        setSearchListener()

        return binding.root
    }

    private fun setSearchListener() {
        binding.svSearchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                KeyboardUtils.hideKeyboard(activity)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchingProduct(newText)
                return true
            }
        })


    }

    private fun initObservers() {
        viewModel.handleProductList().observeForever {  productsList ->
            binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
            binding.rvProducts.adapter = ProductAdapter(productsList, requireActivity(), this)
        }

        viewModel.handleErrorMessage.observeForever { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onProductClick(product: Product) {
        findNavController().navigate(R.id.action_navigation_home_to_product_details)
    }
}