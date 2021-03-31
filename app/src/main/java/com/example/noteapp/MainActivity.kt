package com.example.noteapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewHolder.OnItemClickListener {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences("com.example.noteapp", MODE_PRIVATE)

        recyclerView.layoutManager = LinearLayoutManager(this)

        loadData()

        val intent = Intent(this, DetailsActivity::class.java)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(intent)

        }
    }


    override fun onItemClick(position: Int, title: String, description: String) {
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)

        intent.putExtra("title",title)
        intent.putExtra("desc",description)
        startActivity(intent)
    }


    fun loadData() {

        val json = sharedPreferences.getString("items", null)

        if (json != null) {
            val gson = Gson()
            val turnsType = object : TypeToken<ArrayList<ItemsObject>>() {}.type
            var turns: ArrayList<ItemsObject> = gson.fromJson(json, turnsType)
            recyclerView.adapter = RecyclerAdapter(turns, this)

        }

    }


}