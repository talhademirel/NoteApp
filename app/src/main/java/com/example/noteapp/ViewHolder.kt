package com.example.noteapp

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(itemModel: ItemsObject) {

        val itemTitle: TextView = itemView.findViewById(R.id.cv_title)
        val itemDetail: TextView = itemView.findViewById(R.id.cv_description)

        itemTitle.text = itemModel.title
        itemDetail.text = itemModel.description

    }


    init {
        itemView.setOnClickListener {




            val intent = Intent(itemView.context, DetailsActivity::class.java)
            itemView.context.startActivity(intent)


        }
    }
}