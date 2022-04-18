package com.example.doei.ui.chat.conversation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.doei.models.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow

class ChatViewModel : ViewModel() {
    //TODO apagar mocks
    private val list = listOf<ChatMessage>(
        ChatMessage(0, "Ola, tudo bem? Gostei do produto"),
        ChatMessage(1, "Tudo sim e você? Que ótimo que gostou"),
        ChatMessage(1, "Podemos marcar a entrega?")
    )

    val chatMessages: LiveData<List<ChatMessage>> = MutableStateFlow(list).asLiveData()
}