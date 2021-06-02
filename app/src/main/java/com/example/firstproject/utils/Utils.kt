package com.example.firstproject.utils

object Utils {
fun paresFullName(fullName:String?):Pair<String?,String?>{
    val pares:List<String>? = fullName?.split(" ")
val firstName = pares?.getOrNull(0)
val lastName = pares?.getOrNull(1)
    return firstName to lastName
}
}