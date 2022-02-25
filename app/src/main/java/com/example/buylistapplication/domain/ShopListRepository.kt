package com.example.buylistapplication.domain

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun getShopItem(shopItemId: Int): ShopItem
    fun getShopList(): List<ShopItem>
}