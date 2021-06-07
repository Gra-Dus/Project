package com.example.firstproject.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firstproject.models.Profile
import com.example.firstproject.repositories.PreferencesRepository

class ProfileViewModel: ViewModel() {
    private val  repository: PreferencesRepository = PreferencesRepository
    private val profileData = MutableLiveData<Profile>()
    init {
        Log.d("Al", "init view model")
        profileData.value = repository.getProfile()
    }

    override fun onCleared() {
        super.onCleared()
    Log.d("Al", "view model cleared")
    }
    fun getProfileData():LiveData<Profile> = profileData
    fun saveProfileDate(profile: Profile){
repository.saveProfile(profile)
        profileData.value = profile
    }
}