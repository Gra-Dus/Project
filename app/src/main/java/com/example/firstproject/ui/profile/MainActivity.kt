package com.example.firstproject.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.R
import com.example.firstproject.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity:AppCompatActivity() {
    private val chatAdapter: RecyclerView.Adapter<*>?
    private  lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initViews()
        initViewModel()
    }
    private fun initToolbar(){
        setSupportActionBar(toolbar)
    }
    private fun initViews(){
    with(rv_char_list){
        adapter = chatAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
    }
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }



}