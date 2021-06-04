package com.example.firstproject.ui_profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstproject.R
import com.example.firstproject.models.Hero

class ProfileActivity : AppCompatActivity() {

   var heroObj:Hero = Hero()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }


}