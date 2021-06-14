package com.example.firstproject.repositories

import androidx.lifecycle.MutableLiveData
import com.example.firstproject.data.menedgers.CacheManager
import com.example.firstproject.models.data.Chat
import com.example.firstproject.models.data.User
import com.example.firstproject.models.data.UserItem

object GroupRepository {
    fun loadUsers(): List<User> = CacheManager.loadUsers().value!!
    fun createChat(items: List<UserItem>) {
        val ids = items.map{it.id}
        val users = CacheManager.findUsersByIds(ids).toMutableList()
        val title = users.map{ it.firstName}.joinToString { ", " }
        val chat = Chat(CacheManager.nextChatid(), title, users)
        CacheManager.insertChat(chat)
    }
}