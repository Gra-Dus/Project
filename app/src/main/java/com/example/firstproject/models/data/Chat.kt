package com.example.firstproject.models.data

import com.example.firstproject.models.BaseMessage
import com.example.firstproject.utils.Utils
import java.util.*
import com.example.firstproject.extenshions.shortFormat

data class Chat(
    val id:String,
    val title: String,
    val members:MutableList<User> = mutableListOf(),
    val messages:MutableList<BaseMessage> = mutableListOf(),
    var isArchived:Boolean = false
) {
    enum class ChatType{
        SINGLE,
        GROUP,
        ARCHIVE
    }
    fun unreadableMessageCount():Int{
        return messages.size
    }
    private fun lastMessageData(): Date?{
        return if(messages.size>0) messages.lastOrNull()?.date else Date()
    }
    private fun lastMessageShort():Pair<String,String>{
        return if(messages.size>0) messages.lastOrNull().toString() to "@${messages.lastOrNull()?.from?.firstName!!}" else "Сообщений еще нет" to "@Kelly Jons"
    }
    private fun isSingle()= members.size==1
    fun toChatItem(): ChatItem {
      return if(isSingle()){
            val user = members.first()
            ChatItem(
                id,
                user.avatar,
                Utils.initials(user.firstName,user.lastName) ?:"??",
                "${user.firstName?:""} ${user.lastName ?: ""}",
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageData()?.shortFormat(),
                user.isOnline
            )
        }else{
            ChatItem(
                id,
                null,
                "",
                title,
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageData()?.shortFormat(),
                false,
                ChatType.GROUP,
                lastMessageShort().second
            )
        }
    }
    companion object Factory{
        private var lastid:Int = -1
        fun makeChat(members: MutableList<User>): Chat {
            lastid++
            return Chat(lastid.toString(),members.first().firstName!!,members)
        }
    }

}

