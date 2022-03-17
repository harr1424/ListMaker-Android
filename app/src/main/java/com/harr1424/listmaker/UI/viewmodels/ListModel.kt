package com.harr1424.listmaker

import android.util.Log
import androidx.lifecycle.*
import com.harr1424.listmaker.data.DetailItem
import com.harr1424.listmaker.data.DetailItemDao
import com.harr1424.listmaker.data.MainItem
import com.harr1424.listmaker.data.MainItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class ListViewModel(
    private val mainItemDao: MainItemDao,
    private val detailItemDao: DetailItemDao
): ViewModel() {
    val allMainItems: LiveData<List<MainItem>> = mainItemDao.getAll().asLiveData()
    val allDetailItems: LiveData<List<DetailItem>> = detailItemDao.getAll().asLiveData()

    fun getMainItem(id: Int) : LiveData<MainItem> {
        return mainItemDao.getById(id).asLiveData()
    }

    fun getDetailItems(mainItemId: Int) : LiveData<List<DetailItem>> {
        return detailItemDao.getDetailFromMain(mainItemId).asLiveData()
    }

    fun addMainItem(mainItem: MainItem) {
        // launch a coroutine and call the DAO method to add a MainItem to the database
        viewModelScope.launch(Dispatchers.IO) {
            mainItemDao.insert(mainItem)
        }
    }

    fun addDetailItem(mainItemId: Int, detailItemName: String) {
        val detailItem = DetailItem(mainItemId = mainItemId, detailItemName = detailItemName)

        // launch a coroutine and call the DAO method to add a DetailItem to the database
        viewModelScope.launch(Dispatchers.IO) {
            detailItemDao.insert(detailItem)
        }
    }

    fun deleteMainItem(mainItem: MainItem) {
        // call the DAO method to delete a forageable to the database here
        viewModelScope.launch(Dispatchers.IO) {
            mainItemDao.delete(mainItem)
        }
    }

    fun deleteDetailItem(detailItem: DetailItem) {
        // call the DAO method to delete a forageable to the database here
        viewModelScope.launch(Dispatchers.IO) {
            detailItemDao.delete(detailItem)
        }
    }

    fun deleteDetailsFromMain(mainItemId: Int) {
        // call the DAO method to delete a forageable to the database here
        viewModelScope.launch(Dispatchers.IO) {
            mainItemDao.deleteDetailsFromMain(mainItemId)
        }

    }
    // create a view model factory that takes a MainItemDao and DetailItemDao as properties  and
    //  creates a ForageableViewModel
    class ListViewModelFactory(
        private val mainItemDao: MainItemDao,
        private val detailItemDao: DetailItemDao
        ):
        ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
                return ListViewModel(mainItemDao, detailItemDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }

    // Update functions currently unused, consider adding an edit functionality, especially for
    // MainItems

    // FindByName functions currently unused, consider adding search functionality
}