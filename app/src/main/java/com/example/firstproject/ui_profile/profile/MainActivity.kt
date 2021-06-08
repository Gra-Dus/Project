package com.example.firstproject.ui_profile.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.firstproject.R
import com.example.firstproject.viewmodels.MainViewModel

class MainActivity:AppCompatActivity() {
    private  lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initViews()
        initViewModel()
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private fun initViews(){
    }
    private fun initToolbar(){}

}