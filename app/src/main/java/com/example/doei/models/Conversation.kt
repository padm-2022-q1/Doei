package com.example.doei.models

data class Conversation(
    val name: String?,
    val lastMessage: String?,
    val image: String?,
    var newMessage: Boolean
) {
}