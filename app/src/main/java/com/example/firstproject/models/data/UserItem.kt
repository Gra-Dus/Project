package com.example.firstproject.models.data

data class UserItem(
    val id:String,
    var fullName:String?,
    val initials: String?,
    var avatar:String?,
    var lastActivity: String? = null,
    var isSelected:Boolean = false,
    var isOnline:Boolean = false

)