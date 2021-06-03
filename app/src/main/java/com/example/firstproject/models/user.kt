package com.example.firstproject.models

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
init{
    println("Im not muggle \n"
            + (if (lastName=="damboldor") "my name $firstName $lastName" else "And my name is $firstName $lastName") +
            getIntro()
    )
}
    private fun getIntro() = """
        tu tu tu tuuuu
        
        tu tu tu tuuuutuuuutuuuu!!!! 
       
        
        tu tu tu tuuuu!!!!
        ${"\n\n\n"}
    $firstName $lastName
    """.trimIndent()
    fun printMe()= println("""
    id: $id
firstName: $firstName
lastName: $lastName
avatar:$avatar
rating: $rating
respect:$respect
lastVisit:$lastVisit
isOnline:$isOnline
        """.trimIndent()
        )

companion object Factory{
    private var lastid:Int = -1
    fun makeUser(fullname:String?):User {
        lastid++
        var (firstName, lastName) = Utils.paresFullName(fullname)
        return User( "$lastid","$firstName","$lastName" )
    }
}
}