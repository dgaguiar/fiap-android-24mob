package com.example.simplemarketlist.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemarketlist.R


class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var products = arrayOf("produto1", "Produto 2", "produto 3")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.name.text = products[position]
        holder.value.text = "Not available"
    }

    override fun getItemCount(): Int = products.size

    inner class ItemViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var value: TextView

        init {
            name = itemView.findViewById(R.id.tv_name)
            value = itemView.findViewById(R.id.tv_value)
        }
    }
}