package com.example.firstproject.models.data

import com.example.firstproject.models.data.Chat

data class ChatItem(
    val id:String,
    val avatar:String?,
    val initials: String,
    val title:String,
    val shortDescription:String,
    val messageCount:Int = 0,
    val lastMessageDate: String? = null,
    val isOnline:Boolean = false,
    val chatType: Chat.ChatType = Chat.ChatType.SINGLE,
    val author:String? = null
)