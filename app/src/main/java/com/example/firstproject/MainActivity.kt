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
   lateinit var heroObj:Hero
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Al","OnCreate")
        heroImage = findViewById<ImageView>(R.id.iv_hero)
        textTxt = findViewById<TextView>(R.id.tv_text)
        messageEt = findViewById<EditText>(R.id.et_message)
        sendBtn = findViewById<ImageView>(R.id.iv_send)
textTxt.text = heroObj.askQuestion()
        sendBtn.setOnClickListener(this)
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