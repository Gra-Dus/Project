package com.example.firstproject.extenshions

import com.example.firstproject.models.User
import com.example.firstproject.models.UserView
import com.example.firstproject.utils.Utils
import java.util.*

fun User.toUserView():UserView{
    val nickname = Utils.traneliteration("$firstName $lastName", " ")
    val initials = Utils.initials(firstName,lastName)
    val status = if(lastVisit == null) "Еще ни разу не был" else if (isOnline) "online" else "Последний раз был ${lastVisit?.humanizeDiff()}"
   return UserView(
       id,
   fullName = "$firstName $lastName",
   nickName = nickname,
   initials = initials,
   avatar = avatar,
   status = status)
}





