package com.harr1424.listmaker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harr1424.listmaker.data.Item


class ListViewModel : ViewModel() {
    private val _mainList = MutableLiveData<MutableList<Item>>()
    val mainList: LiveData<MutableList<Item>> = _mainList

    private val _detailList = MutableLiveData<MutableList<MutableList<Item>>>()
    val detailList: LiveData<MutableList<MutableList<Item>>> = _detailList

    init {
        _mainList.value = arrayListOf()
        _detailList.value = arrayListOf(arrayListOf())
    }

    fun addItemMainList(item: Item) {
        if (_mainList.value?.contains(item) == false) {
            _mainList.value?.add(item)
            _mainList.value = _mainList.value
        }
    }

    fun deleteItemMainList(item: Item) {
        if (_mainList.value?.contains(item) == true) {
            _mainList.value?.remove(item)
            _mainList.value = _mainList.value
        }
    }

    fun addItemDetailList(mainItem: Item, detailItem: Item) {
        val detailIndex = _mainList.value?.indexOf(mainItem)
        if (_detailList.value?.elementAt(detailIndex!!)?.contains(detailItem) == false) {
            _detailList.value?.elementAt(detailIndex!!)?.add(detailItem)
            _detailList.value = _detailList.value
        }
    }

    fun deleteItemDetailList(mainItem: Item, detailItem: Item) {
        val detailIndex = _mainList.value?.indexOf(mainItem)
        _detailList.value?.elementAt(detailIndex!!)?.remove(detailItem)
    }
}