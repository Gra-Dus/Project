package com.example.firstproject.data.menedgers

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.firstproject.models.data.Chat
import com.example.firstproject.models.data.User
import com.example.firstproject.utils.DataGenerator.createChats
import com.example.firstproject.utils.DataGenerator.createUsers


object CacheManager {
private val users = MutableLiveData(createUsers(10))
private val chats = MutableLiveData(createChats(15))

    fun loadChats():MutableLiveData<List<Chat>>{
        return chats
    }

    fun findUsersByIds(ids: List<String>): List<User>{
        return users.value!!.filter{ ids.contains(it.id)}
    }
    fun nextChatid():String{
       return "${chats.value!!.size}"
    }
    fun insertChat(chat:Chat){
        chats.value!!.toMutableList() +=chat
    }
}