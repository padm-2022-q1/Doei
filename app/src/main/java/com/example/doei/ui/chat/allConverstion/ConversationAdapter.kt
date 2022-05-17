package com.example.doei.ui.chat.allConverstion

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.doei.R
import com.example.doei.databinding.AdapterConversationBinding
import com.example.doei.domain.models.Conversation

class ConversationAdapter(
    private val activity: Activity,
    private val conversationsList: List<Conversation>,
    private val listener: Listener
    ): RecyclerView.Adapter<ConversationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterConversationBinding.inflate(LayoutInflater.from(activity.applicationContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val conversation = conversationsList[position]
        holder.tvName.text = conversation.name
        holder.tvLastMessage.text = conversation.lastMessage
        if (conversation.newMessage) {
            holder.tvLastMessage.setTextColor(Color.BLACK)
            holder.tvLastMessage.setTypeface(null, Typeface.BOLD)
        } else {
            holder.tvLastMessage.setTextColor(Color.GRAY)
            holder.tvLastMessage.setTypeface(null, Typeface.NORMAL)
        }
        holder.ivAlert.isVisible = conversation.newMessage

        Glide
            .with(activity.applicationContext)
            .load(R.drawable.sofa_antigo)
            .into(holder.sivProfile)
        holder.itemView.setOnClickListener {
            listener.onChatClicked()
        }
    }

    override fun getItemCount(): Int {
        return conversationsList.size
    }

    class ViewHolder(view: AdapterConversationBinding): RecyclerView.ViewHolder(view.root) {
        val tvName = view.tvName
        val tvLastMessage = view.tvLastMessage
        val sivProfile = view.sivProfile
        val ivAlert = view.ivAlert
    }

    interface Listener {
        fun onChatClicked()
    }
}