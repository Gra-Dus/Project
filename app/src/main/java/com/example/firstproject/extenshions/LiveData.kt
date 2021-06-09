package com.example.firstproject.extenshions

import androidx.lifecycle.MutableLiveData

fun <T> mutableLiveData(defauValue:T? = null): MutableLiveData<T>{
    val data = MutableLiveData<T>()
    if(defauValue!=null){
        data.value = defauValue
    }
    return data
}