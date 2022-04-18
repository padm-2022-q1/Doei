package com.example.doei.ui.chat.conversation

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doei.databinding.AdapterMessageBinding
import com.example.doei.models.ChatMessage

class MessageAdapter(
    private val list: List<ChatMessage>,
    private val activity: Activity
): RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    companion object {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =  AdapterMessageBinding.inflate(LayoutInflater.from(activity.applicationContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mensagem = list[position]
        val idUsuario = 0
        if (mensagem.idUsuario == idUsuario){
            holder.clMessages.setBackgroundColor(Color.BLUE)
        }else {
            holder.clMessages.setBackgroundColor(Color.WHITE)
        }
        holder.tvMessage.text = mensagem.text
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: AdapterMessageBinding): RecyclerView.ViewHolder(view.root) {
        val clMessages = view.clMessage
        val tvMessage = view.tvMessage
    }
}