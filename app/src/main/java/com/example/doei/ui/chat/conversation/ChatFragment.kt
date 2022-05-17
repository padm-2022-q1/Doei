package com.example.doei.ui.chat.conversation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.doei.R
import com.example.doei.databinding.ChatFragmentBinding
import com.example.doei.domain.models.Conversation

const val CONVERSATION_INFO = "CONVERSATION_INFO"
class ChatFragment : Fragment() {

    private lateinit var viewModel: ChatViewModel
    private lateinit var binding: ChatFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChatFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ChatViewModel::class.java]


        val conversation = arguments?.getParcelable<Conversation>(CONVERSATION_INFO)
        binding.tvChatName.text = conversation?.name
        Glide.with(requireActivity())
            .load(R.drawable.sofa_antigo)
            .into(binding.sivProfile)

        initObservers()
    }

    private fun initObservers() {
        viewModel.chatMessages.observeForever { chatMessages ->
            binding.rvMessages.layoutManager = LinearLayoutManager(requireContext())
            binding.rvMessages.adapter = MessageAdapter(chatMessages, requireActivity())
        }
    }
}