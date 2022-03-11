package com.harr1424.listmaker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ListViewModel : ViewModel() {
    private val _list = MutableLiveData<MutableList<String>?>()
    val list: LiveData<MutableList<String>?> = _list

    init {
        _list.value = ArrayList()
    }

    fun addItem(item: String) {
        _list.value?.add(item)
        _list.value = _list.value
    }

    fun deleteItem(item: String) {
        _list.value?.remove(item)
        _list.value = _list.value
    }
}