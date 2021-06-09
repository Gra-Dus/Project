package com.example.firstproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.firstproject.extenshions.mutableLiveData
import com.example.firstproject.models.DataGenerator
import com.example.firstproject.models.data.ChatItem
import com.example.firstproject.repositories.ChatRepository

class MainViewModel: ViewModel() {
    private val charRepository = ChatRepository
    private val chats = mutableLiveData(loadChats())
    fun getChatData() :LiveData<List<ChatItem>>{
        return chats
    }
    private fun loadChats():List<ChatItem>?{
       val chats = charRepository.loadChats()
        return chats?.map{it.toChatItem()}
            ?.sortedBy { it.id.toInt() }
    }

    fun addItems() {
       // val newItems = DataGenerator.generateChatsWithOffset(chats.value!!.size, 5).map{it.toChatItem()}
       //     val copy = chats.value!!.toMutableList()
       // copy.addAll(newItems)
       // chats.value = copy.sortedBy { it.id.toInt() }
    }
}