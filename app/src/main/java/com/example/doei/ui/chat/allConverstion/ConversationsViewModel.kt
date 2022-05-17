package com.example.doei.ui.chat.allConverstion

import androidx.lifecycle.*
import com.example.doei.domain.models.Conversation
import kotlinx.coroutines.flow.MutableStateFlow

class ConversationsViewModel: ViewModel() {
    //TODO apagar esses mocks quando criar repositório
    private val list: List<Conversation> = listOf(
        Conversation(
            "Sofa antigo",
            "Podemos combinar o dia? ",
            "",
            true
        ),
        Conversation(
            "Ventilador",
            "Tem algumas avarias",
            "",
            false
        )
    )



    var conversationsList: LiveData<List<Conversation>> = MutableStateFlow(list).asLiveData()

}
