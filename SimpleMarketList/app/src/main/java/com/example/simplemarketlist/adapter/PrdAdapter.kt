package com.example.simplemarketlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.simplemarketlist.R
import com.example.simplemarketlist.models.Products

class PrdAdapter(private val prdList: ArrayList<Products>) :
    RecyclerView.Adapter<PrdAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPrd = prdList[position]
        holder.tvName.text = currentPrd.prdName
    }

    override fun getItemCount(): Int {
        return prdList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvName : TextView = itemView.findViewById(R.id.tv_name)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}