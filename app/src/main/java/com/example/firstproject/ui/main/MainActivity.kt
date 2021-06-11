package com.example.firstproject.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstproject.R
import com.example.firstproject.models.data.UserItem
import com.example.firstproject.ui.adapters.ChatAdapter
import com.example.firstproject.ui.adapters.ChatItemTouchHelperCallback
import com.example.firstproject.ui.group.GroupActivity
import com.example.firstproject.viewmodels.MainViewModel
import com.google.android.material.chip.Chip
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
        setSupportActionBar(toolbar_main)
    }
    @SuppressLint("ShowToast")
    private fun initViews(){
        chatAdapter = ChatAdapter{
            Snackbar.make(rv_char_list, "Click on ${it.title}", Snackbar.LENGTH_LONG)
        }
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val touchCallback = ChatItemTouchHelperCallback(chatAdapter){
            viewModel.addToArchive(it.id)
            Snackbar.make(rv_char_list,"Вы точно хотите добавить ${it.title} в архив", Snackbar.LENGTH_LONG).show()

        }
        val touchHelper = ItemTouchHelper(touchCallback)
        touchHelper.attachToRecyclerView(rv_char_list)
    with(rv_char_list){
        adapter = chatAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        addItemDecoration(divider)
    }
        fab_main.setOnClickListener{
            val intent = Intent(this, GroupActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getChatData().observe(this, { chatAdapter.updateData(it)})
    }



}