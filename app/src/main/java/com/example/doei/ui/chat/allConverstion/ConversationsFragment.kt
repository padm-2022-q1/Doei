package com.example.doei.ui.chat.allConverstion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doei.databinding.FragmentConversationsBinding

class ConversationsFragment: Fragment() {

    private lateinit var binding: FragmentConversationsBinding
    private lateinit var viewModel: ConversationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConversationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ConversationsViewModel::class.java]
        initObservers()
    }

    private fun initObservers() {
        viewModel.conversationsList.observeForever { conversations ->
            binding.rvConversations.layoutManager =  LinearLayoutManager(requireContext())
            binding.rvConversations.adapter = ConversationAdapter(requireActivity(), conversations)

        }
    }
}