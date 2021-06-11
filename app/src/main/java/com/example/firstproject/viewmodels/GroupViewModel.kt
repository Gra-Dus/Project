package com.example.firstproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.firstproject.extenshions.mutableLiveData
import com.example.firstproject.models.data.UserItem
import com.example.firstproject.repositories.GroupRepository

class GroupViewModel: ViewModel() {
    private val query = mutableLiveData("")
    private val groupRepository = GroupRepository
    private val userItems = mutableLiveData(loadUsers())
    private val selectedItems = Transformations.map(userItems){users -> users.filter{it.isSelected}}

    fun getUsersData():LiveData<List<UserItem>>{
        val result = MediatorLiveData<List<UserItem>>()
        val filterF = {
            val queryStr = query.value!!
            val users = userItems.value!!
            result.value = if(queryStr.isEmpty()) users
            else users.filter { it.fullName!!.contains(queryStr,true) }
        }
        result.addSource(userItems){filterF.invoke()}
        result.addSource(query){filterF.invoke()}
        return result
    }
   fun getSelectedData():LiveData<List<UserItem>> = selectedItems
    fun handleSelectedItems(userId: String){
        userItems.value = userItems.value!!.map{
            if(it.id == userId) it.copy(isSelected = !it.isSelected)
            else it
        }
    }
    fun handleRemoveChip(userId: String) {
        userItems.value = userItems.value!!.map{
            if(it.id == userId) it.copy(isSelected = false)
            else it
        }
    }

    private fun loadUsers(): List<UserItem> = groupRepository.loadUsers().map{it.toUserItems()}
    fun handleSearchQuery(text: String) {
        query.value = text
    }

    fun handleCreateGroup() {
        groupRepository.createChat(selectedItems.value!!)
    }


}