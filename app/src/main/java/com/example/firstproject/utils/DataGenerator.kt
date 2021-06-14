package com.example.firstproject.utils

import android.graphics.Insets.add
import androidx.core.graphics.Insets.add
import androidx.core.view.OneShotPreDrawListener.add
import com.example.firstproject.extenshions.TimeUnits
import com.example.firstproject.extenshions.add
import com.example.firstproject.models.BaseMessage
import com.example.firstproject.models.data.Chat
import com.example.firstproject.models.data.ChatItem
import com.example.firstproject.models.data.User
import java.util.*
import java.util.concurrent.TimeUnit

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
        "Pauly",
        "Potter"
    )
    private val messages  = listOf(
        "Ok",
        "Enjoy",
        "Come in",
        "Fuck off",
        "Meet - no, Vegetables - yes"
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
    fun createChats(N:Int):List<Chat>{
        for(i in 1..N){
            newChat()
        }
        return chats
    }

    private fun newChat() {
        createUsers(10)
        val copy = users
        val members = mutableListOf<User>()
        var user:User
        for (i in 0..(2..firstNames.size* lastNames.size).random()){
            user = copy[(0 until users.size).random()]
            members+=user
            copy -= user
        }
        val chat = Chat.makeChat(members)
        chats+= chat
        for (i in members){
            when((0..1).random()){
                1 -> BaseMessage.makeMessage(i,chat,Date().add((-900..0).random(), TimeUnits.values().random()),"text", messages.random())
                else -> continue
            }
        }
    }

}