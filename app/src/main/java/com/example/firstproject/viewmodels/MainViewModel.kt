package com.example.firstproject.viewmodels

import androidx.lifecycle.ViewModel
import com.example.firstproject.repositories.ChatRepository

class MainViewModel: ViewModel() {
    private  val chatRepository = ChatRepository
}