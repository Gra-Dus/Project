package com.example.firstproject.repositories

import androidx.lifecycle.MutableLiveData
import com.example.firstproject.data.menedgers.CacheManager
import com.example.firstproject.models.data.Chat
import com.example.firstproject.models.data.User


object ChatRepository {
    private val chats = CacheManager.loadChats()

    fun loadChats() : MutableLiveData<List<Chat>> {
        return chats
    }

    fun update(chat: Chat) {
        val copy = chats.value!!.toMutableList()
        val ind = chats.value!!.indexOfFirst { it.id == chat.id }
        if (ind == -1) return
        copy[ind] = chat
        chats.value = copy
    }

    fun find(chatId: String): Chat? {
        val ind = chats.value!!.indexOfFirst {
            it.id == chatId
        }
        return chats.value!!.getOrNull(ind)
    }
}