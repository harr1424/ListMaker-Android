package com.harr1424.listmaker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ListViewModel : ViewModel() {
    private val _list = MutableLiveData<MutableMap<String, MutableList<String>>>()
    val list: LiveData<MutableMap<String, MutableList<String>>> = _list

    init {
        _list.value = mutableMapOf()
    }

    fun addItemMainList(item: String) {
        _list.value?.put(item, mutableListOf())
        _list.value = _list.value
    }

    fun deleteItemMainList(item: String) {
        _list.value?.remove(item)
        _list.value = _list.value
    }

    fun addItemDetailList(mainItem: String, detailItem: String) {
        // if this resets the map, try using + operator as described
        // https://kotlinlang.org/docs/map-operations.html#plus-and-minus-operators

        _list.value?.put(mainItem, mutableListOf(detailItem))

    }
}