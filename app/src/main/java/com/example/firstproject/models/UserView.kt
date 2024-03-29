package com.example.firstproject.models

import com.example.firstproject.extenshions.format

class UserView(
    val id:String,
    val fullName:String,
    val nickName:String,
    val avatar:String? = null,
    val status:String? ="offline",
    val initials:String?
) {
fun printMe(){
    println("""
          id:$id
          fullName: $fullName
          nickName:$nickName
          avatar:$avatar
          status:$status
          initials:$initials
        """.trimIndent())

}
}