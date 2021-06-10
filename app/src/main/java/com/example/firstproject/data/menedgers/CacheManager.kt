package com.example.firstproject.data.menedgers

import androidx.lifecycle.MutableLiveData
import com.example.firstproject.models.data.Chat
import com.example.firstproject.models.data.User

object CacheManager {
private val users = listOf<User>()
private val chats = listOf<User>()
    fun loadChats():MutableLiveData<List<Chat>>?{
        return null
    }

    fun findUsersByIds(ids: List<String>): List<User>{
        return users
            //.value!!.filter{ ids.contains(it.id)}
    }
    fun nextChatid():String{
       return "${chats.size}"
    }
    fun insertChat(chat:Chat){
     //   val copy = chats.value!!.toMulableList()
     //   copy.add(chat)
     //   chats.value = copy
    }
}