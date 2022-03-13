package com.harr1424.listmaker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harr1424.listmaker.data.Item


class ListViewModel : ViewModel() {
    private val _list = MutableLiveData<MutableList<Item>>()
    val list: LiveData<MutableList<Item>> = _list

    init {
        _list.value = arrayListOf()
    }

    fun addItemMainList(item: Item) {
        if (_list.value?.contains(item) == false) {
            _list.value?.add(item)
            _list.value = _list.value
        }
        Log.d("AddItem", list.value.toString())

    }

    fun deleteItemMainList(item: Item) {
        if (_list.value?.contains(item) == true) {
            _list.value?.remove(item)
            _list.value = _list.value
        }
    }

    fun addItemDetailList(mainItem: Item, detailItem: String) {
        val targetItem = list.value?.indexOf(mainItem)
        if (targetItem != null) {
            _list.value?.elementAt(targetItem)?.detailItems?.add(detailItem)
        }
        Log.d("DetailListAddItem", list.value.toString())
    }

    fun deleteItemDetailList(mainItem: Item, detailItem: String) {
        val targetItem = list.value?.indexOf(mainItem)
        if (targetItem != null) {
            _list.value?.elementAt(targetItem)?.detailItems?.remove(detailItem)
        }
    }
}