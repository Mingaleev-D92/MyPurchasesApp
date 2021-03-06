package com.example.mypurchasesapp.arch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypurchasesapp.AppDatabase
import com.example.mypurchasesapp.database.entity.ItemEntity
import kotlinx.coroutines.launch

/**
 * @author Mingaleev D. 08.06.2022
 **/
class PurchasesViewModel : ViewModel() {

    private lateinit var repository: PurchasesRepository

    val itemEntitiesLiveData = MutableLiveData<List<ItemEntity>>()

    fun init(appDatabase: AppDatabase) {
        repository = PurchasesRepository(appDatabase)

        viewModelScope.launch {
            val items = repository.getAllItems()
            itemEntitiesLiveData.postValue(items)
        }
    }

    fun insertItem(itemEntity: ItemEntity) {
        repository.insertItem(itemEntity)
    }

    fun deleteItem(itemEntity: ItemEntity) {
        repository.deleteItem(itemEntity)
    }
}