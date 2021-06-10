package com.example.firstproject.repositories

import com.example.firstproject.data.menedgers.CacheManager
import com.example.firstproject.models.data.Chat
import com.example.firstproject.models.data.User
import com.example.firstproject.models.data.UserItem
import javax.security.auth.callback.CallbackHandler

object GroupRepository {
    fun loadUsers():List<User> = listOf()
    fun createChat(items: List<UserItem>) {
        val ids = items.map{it.id}
        val users = CacheManager.findUsersByIds(ids)
        val title = users.map{ it.firstName}.joinToString { ", " }
        val chat = Chat(CacheManager.nextChatid(), title, users)
        CacheManager.insertChat(chat)
    }
}