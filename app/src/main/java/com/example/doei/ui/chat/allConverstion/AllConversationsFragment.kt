package com.example.doei.ui.chat.allConverstion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.doei.R
import com.example.doei.databinding.AllConversationsFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class AllConversationsFragment : Fragment() {

    private lateinit var viewModel: AllConversationsViewModel
    private lateinit var binding: AllConversationsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AllConversationsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AllConversationsViewModel::class.java]
        binding.pager.adapter = ConversationsCollectionAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when(position) {
                0 -> "Meus Interesses"
                1 -> "Meus Produtos"
                else -> "Outros"
            }
        }.attach()
    }
}