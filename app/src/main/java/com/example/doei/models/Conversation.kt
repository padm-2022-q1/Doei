package com.example.doei.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Conversation(
    val name: String?,
    val lastMessage: String?,
    val image: String?,
    var newMessage: Boolean
): Parcelable {
}