package com.example.firstproject

import androidx.core.widget.TextViewCompat
import com.example.firstproject.models.User
import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun test_instance(){
        val user2 = User("2","Kely","Peggy")
    }
@Test
fun test_factory(){
  //  val user = User.makeUser("Klary Vary")
   // val user2= User.makeUser("Klary Gary")
  //  val user3 = user.copy("2",lastName = "Henry")
//print("$user3")
}
    @Test
    fun test_decomposition(){
        val user = User.makeUser("Numb Clown")
    fun getUserInfo() = user
        val (id, firstName, lastName) = getUserInfo()
     //   println("$id,$firstName,$lastName")
      //  println("${user.component1()},${user.component2()},$${user.component3()}")
    }
    @Test
    fun test_copy(){
        val user = User.makeUser("Kondratiy Wally")
        var user2 = user.copy(lastVisit = Date())
        var user3 = user.copy(lastName = "Wonka", lastVisit = Date())

        if(user.equals(user2)){
            println("equals data and hash \n ${user.hashCode()} $user \n ${user2.hashCode()} $user2")
        }else
        {
            println("not equals and hash \\n ${user.hashCode()} $user \\n ${user2.hashCode()} $user2\" ")
        }
        if(user === user2){
            println("equals data and hash ${System.identityHashCode(user)} ${System.identityHashCode(user2)}")
        }else
        {
            println("not equals data and hash  ${System.identityHashCode(user)} ${System.identityHashCode(user2)}")
        }

    }
}