package com.example.firstproject.models

import com.example.firstproject.models.data.Chat
import java.util.*

class ImageMessage(
    id:String,
    from:User?,
    chat: Chat,
    IsIncoming:Boolean = false,
    date: Date = Date(),
    val image: String?
):BaseMessage(id,from,chat,IsIncoming,date) {
    override fun formatMessage(): String ="id:$id ${from?.firstName} " +
            (if(IsIncoming)"получил" else "отправил") +
            "\"$image\""
}