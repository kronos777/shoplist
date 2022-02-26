package com.example.buylistapplication.presentation

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buylistapplication.data.ShopListRepositoryImpl
import com.example.buylistapplication.domain.*

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)


    //создаем объект livedata
    val shopList = getShopListUseCase.getShopList()
/*
    fun getShopList() {
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

 */

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopShopItem(shopItem)

    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)

    }



}