package com.example.firstproject.utils

import com.example.firstproject.models.data.Chat
import com.example.firstproject.models.data.ChatItem
import com.example.firstproject.models.data.User

object DataGenerator {
    private val chats = mutableListOf<Chat>()
    private val firstNames = listOf(
        "John",
       "Kendra",
        "Henry"
    )
    private val lastNames = listOf(
        "Black",
        "Bury",
        "Puly"
    )
    private var users = mutableListOf<User>()
    fun createUsers(N:Int):MutableList<User>{
    for(i in 1..N){
        newUser()
    }
        return users
    }

    private fun newUser() {
        val first = firstNames.random()
        val last = lastNames.random()
        users += User.makeUser("$first $last")
    }
    fun createChat(N:Int):List<Chat>{
        for(i in 1..N){
            newChat()
        }
        return chats
    }

    private fun newChat() {
        val copy = users
        val members = mutableListOf<User>()
        var user:User
        for (i in 0..(1..firstNames.size* lastNames.size).random()){
            user = copy[(0..users.size).random()]
            members+=user
            copy -= user
        }
        chats+= Chat.makeChat(members)
    }

}