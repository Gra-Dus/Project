package com.example.firstproject

import com.example.firstproject.extenshions.TimeUnits
import com.example.firstproject.extenshions.add
import com.example.firstproject.extenshions.format
import com.example.firstproject.extenshions.toUserView
import com.example.firstproject.models.*
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
        var user2 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        var user3 = user.copy(lastVisit = Date().add(-3, TimeUnits.DAY))
        var user4 = user.copy(lastName = "Wonka", lastVisit = Date().add(2,TimeUnits.HOUR))
    }
    @Test
    fun test_data_maping(){
        val user = User.makeUser("Клоп Андрей")
        val newuser = user.copy(lastVisit = Date().add(-900, TimeUnits.HOUR))
        println(newuser)
        val userView = newuser.toUserView()
        userView.printMe()
    }
    @Test
    fun test_abstract_factory(){
        val user = User.makeUser("Классика Антон")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"),payload = "any text message",type= "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"),payload = "any image url",type= "image")
        when(imgMessage){
            is TextMessage-> println("this is text message")
            is ImageMessage-> println("this is img message")
        }
        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())

    }
}