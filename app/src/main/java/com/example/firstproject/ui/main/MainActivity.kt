package com.example.firstproject.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.R
import com.example.firstproject.adapters.ChatAdapter
import com.example.firstproject.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity:AppCompatActivity() {
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var viewModel: MainViewModel
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
    @SuppressLint("ShowToast")
    private fun initViews(){
        chatAdapter = ChatAdapter{
            Snackbar.make(rv_char_list, "Click on ${it.title}", Snackbar.LENGTH_LONG)
        }
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

    with(rv_char_list){
        adapter = chatAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        addItemDecoration(divider)
    }
        fab.setOnClickListener{
            viewModel.addItems()
        }
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getChatData().observe(this, Observer { chatAdapter.updateData(it)})
    }



}