package com.example.firstproject.models

import com.example.firstproject.models.data.Chat
import com.example.firstproject.models.data.User
import java.util.*

class TextMessage(
    id:String,
    from: User?,
    chat: Chat,
    IsIncoming:Boolean = false,
    date: Date = Date(),
    var text :String?
):BaseMessage(id,from,chat,IsIncoming,date) {
    override fun formatMessage(): String ="id:$id ${from?.firstName} " +
            (if(IsIncoming)"получил" else "отправил") +
            "\"$text\""
}