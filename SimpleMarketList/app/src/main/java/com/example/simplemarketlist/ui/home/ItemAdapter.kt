package com.example.simplemarketlist.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemarketlist.R
import java.util.UUID

class ItemAdapter(
    private val items: List<ItemList>,
    private val clickListener: ItemClickListener
): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (items != null) {
            holder.name.text = items[position].name
            holder.value.text = items[position].value
            holder.id = items[position].id
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ItemViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var value: TextView
        var id: UUID
        var container: LinearLayout

        init {
            name = itemView.findViewById(R.id.tv_name)
            value = itemView.findViewById(R.id.tv_value)
            id = UUID.randomUUID()
            container = itemView.findViewById(R.id.linearLayout)

            val item = ItemList(name.text as String, value.text as String, id)

            container.setOnClickListener {
                clickListener.editTaskItem(item)
            }
        }
    }
}