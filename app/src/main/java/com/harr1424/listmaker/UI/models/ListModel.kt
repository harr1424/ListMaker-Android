package com.harr1424.listmaker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ListViewModel : ViewModel() {
    private val _mainList = MutableLiveData<MutableList<String>>()
    val mainList: LiveData<MutableList<String>> = _mainList

    private val _detailList = MutableLiveData<MutableList<MutableList<String>>>()
    ;private val detailList: LiveData<MutableList<MutableList<String>>> = _detailList

    init {
        _mainList.value = arrayListOf()
        _detailList.value = arrayListOf(arrayListOf())
    }

    fun addItemMainList(item: String) {
        if (_mainList.value?.contains(item) == false) {
            _mainList.value?.add(item)
            _mainList.value = _mainList.value
            }
    }

    fun deleteItemMainList(item: String) {
        _mainList.value?.remove(item)
        _mainList.value = _mainList.value
    }

    fun addItemDetailList(mainItem: String, detailItem: String) {
        val detailIndex = _mainList.value?.indexOf(mainItem)
        if (_detailList.value?.elementAt(detailIndex!!)?.contains(detailItem) == false) {
            _detailList.value?.elementAt(detailIndex!!)?.add(detailItem)
            _detailList.value = _detailList.value
        }
    }

    fun deleteItemDetailList(mainItem: String, detailItem: String) {
        val detailIndex = _mainList.value?.indexOf(mainItem)
        _detailList.value?.elementAt(detailIndex!!)?.remove(detailItem)
    }
}