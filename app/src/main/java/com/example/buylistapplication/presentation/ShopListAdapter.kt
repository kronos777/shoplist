package com.example.buylistapplication.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.buylistapplication.R
import com.example.buylistapplication.domain.ShopItem
import java.lang.RuntimeException


class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

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
        val shopItem = shopList[position]
        /*val status = if(shopItem.enabled) {
            "Active"
        } else {
            "Not active"
        }*/
        viewHolder.tvName.text = "${shopItem.name}"
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.view.setOnLongClickListener {
            true
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
        val item = shopList[position]
        return if(item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val  VIEW_TYPE_ENABLED = 10
        const val  VIEW_TYPE_DISABLED = 11
        const val  MAX_POOL_SIZE = 15
    }


    override fun getItemCount(): Int {
        return shopList.size
    }

    class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)

    }
}