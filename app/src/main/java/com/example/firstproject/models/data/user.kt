package com.example.firstproject.models.data

import com.example.firstproject.extenshions.humanizeDiff
import com.example.firstproject.utils.Utils
import java.util.*

data class User (
    val id:String,
    var firstName:String?,
    var lastName:String?,
    var avatar:String?,
    var rating:Int = 0,
    var respect:Int=0,
    var lastVisit: Date? = null,
    var isOnline:Boolean = false

)
{

    constructor(id:String,firstName:String?, lastName:String?): this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )
    constructor(id:String):this(id,"Fedor","damboldor")

    fun toUserItems(): UserItem {
        val lastActivity = when{
        lastVisit == null -> "Еще ни разу не заходил"
        isOnline -> "online"
        else -> "Последний раз был ${lastVisit!!.humanizeDiff()}"}
        return UserItem(
            id,
            "$firstName $lastName",
            Utils.initials(firstName,lastName),
            avatar,
            lastActivity,
            false,
            isOnline
        )
    }

    companion object Factory{
    private var lastid:Int = -1
    fun makeUser(fullname:String?): User {
        lastid++
        var (firstName, lastName) = Utils.paresFullName(fullname)
        return User( "$lastid","$firstName","$lastName" )
    }
}
}