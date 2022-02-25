package com.example.buylistapplication.domain

class DeleteShopItemUseCase (private val shopListRepository: ShopListRepository) {
    fun deleteShopShopItem(shopItem: ShopItem) {
        return shopListRepository.deleteShopShopItem(shopItem)
    }
}