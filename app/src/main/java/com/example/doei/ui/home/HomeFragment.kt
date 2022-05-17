package com.example.doei.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doei.R
import com.example.doei.databinding.FragmentHomeBinding
import com.example.doei.domain.models.Product

class HomeFragment : Fragment(), ProductAdapter.Listener {

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initObservers()

        return binding.root
    }

    private fun initObservers() {
        viewModel.productList.observeForever {
            _binding?.rvProducts?.layoutManager = LinearLayoutManager(requireContext())
            _binding?.rvProducts?.adapter = ProductAdapter(it, requireActivity(), this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onProductClick(product: Product) {
        findNavController().navigate(R.id.action_navigation_home_to_product_details)
    }
}