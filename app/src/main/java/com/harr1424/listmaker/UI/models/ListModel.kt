package com.harr1424.listmaker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ListViewModel : ViewModel() {
    private val _mainList = MutableLiveData<MutableList<String>?>()
    val mainList: LiveData<MutableList<String>?> = _mainList

    init {
        _mainList.value = ArrayList()
    }

    fun addMainListItem(item: String) {
        _mainList.value?.add(item)
        _mainList.value = _mainList.value
    }

    fun deleteMainListItem(item: String) {

    }
}