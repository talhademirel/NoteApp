package com.example.noteapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items.view.*

class ViewHolder(itemView: View, private val listener: ViewHolder.OnItemClickListener) :
    RecyclerView.ViewHolder(itemView){


    fun bindItems(itemModel: ItemsObject) {

        val itemTitle: TextView = itemView.cv_title
        val itemDetail: TextView = itemView.cv_description

        itemTitle.text = itemModel.title
        itemDetail.text = itemModel.description

        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition,itemModel.title,itemModel.description)
        }

    }


    interface OnItemClickListener {
        fun onItemClick(position: Int,title: String,description: String)
    }
}