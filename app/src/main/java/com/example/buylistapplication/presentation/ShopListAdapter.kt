package com.example.buylistapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.buylistapplication.R
import com.example.buylistapplication.domain.ShopItem


class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {


    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null
    //var onShopItemLongClickListener: OnShopItemLongClickListener? = null

/*    var shopList = listOf<ShopItem>()
        set(value) {
            val callback = ShopListDiffCallback(shopList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
            //notifyDataSetChanged()
        }
*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown runtime exeption $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        //val shopItem = shopList[position]
        /*val status = if(shopItem.enabled) {
            "Active"
        } else {
            "Not active"
        }*/
        viewHolder.tvName.text = "${shopItem.name}"
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        viewHolder.view.setOnClickListener{
            onShopItemClickListener?.invoke(shopItem)
        }
/*
        if(shopItem.enabled) {
            viewHolder.tvName.text = "${shopItem.name} $status"
            viewHolder.tvCount.text = shopItem.count.toString()
            viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context, android.R.color.holo_purple))
        } else {
            viewHolder.tvName.text = ""
            viewHolder.tvCount.text = ""
            viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context, android.R.color.white))
        }
*/
    }



/* решение на стек оверфлов для утранение бага рекуклер*/
  override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if(item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }




/*
    interface OnShopItemLongClickListener {
        fun onShopItemLongClick(shopItem: ShopItem)
    }*/

    companion object {
        const val  VIEW_TYPE_ENABLED = 10
        const val  VIEW_TYPE_DISABLED = 11
        const val  MAX_POOL_SIZE = 15
    }

}