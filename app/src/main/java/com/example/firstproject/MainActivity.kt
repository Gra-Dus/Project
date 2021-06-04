package com.example.firstproject

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.firstproject.models.Hero

class MainActivity : AppCompatActivity(), View.OnClickListener {
   lateinit var heroImage:ImageView
   lateinit var textTxt:TextView
   lateinit var messageEt:EditText
   lateinit var sendBtn:ImageView
   var heroObj:Hero = Hero()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        heroImage = findViewById<ImageView>(R.id.iv_hero)
        textTxt = findViewById<TextView>(R.id.tv_text)
        messageEt = findViewById<EditText>(R.id.et_message)
        sendBtn = findViewById<ImageView>(R.id.iv_send)
        Log.d("Al","OnCreate1")
        val status = savedInstanceState?.getString("STATUS")?: Hero.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION")?: Hero.Question.NAME.question
        Log.d("Al","OnCreate $status $question")
        heroObj.status = Hero.Status.valueOf(status)
        Log.d("Al","OnCreate $status $question")
        heroObj.question.question = question
        Log.d("Al","OnCreate4")

        val (r,g,b) = heroObj.status.color
        heroImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
        Log.d("Al","OnCreate3")

        textTxt.text = heroObj.askQuestion()
        sendBtn.setOnClickListener(this)
        Log.d("Al","OnCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Al","onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Al","OnStart")

    }
    override fun onPause(){
        super.onPause()
        Log.d("Al","OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Al","OnStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Al","OnResume")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Al","OnDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("STATUS",heroObj.status.name)
        outState.putString("QUESTION",heroObj.question.question)
        Log.d("Al","onSaveInstanceState ${heroObj.status.name} \n ${heroObj.question.question}")
    }
    override fun onClick(v: View?) {
       if(v?.id == R.id.iv_send){
           val (phrase, color) = heroObj.listenAnswer(messageEt.text.toString())
           messageEt.setText("")
           val (r,g,b) = color
           heroImage.setColorFilter(Color.rgb(r,g,b), PorterDuff.Mode.MULTIPLY)
           textTxt.text = phrase
       }
    }

}