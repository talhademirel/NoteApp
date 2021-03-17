package com.example.noteapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    var items = ArrayList<ItemsObject>()

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        sharedPreferences = this.getSharedPreferences("com.example.noteapp", MODE_PRIVATE)

    }

    fun saveButton(view: View) {

        val json = sharedPreferences.getString("items", null)
        val title = editTextTextTitle.text.toString()
        val desc = editTextTextDescription.text.toString()

        if (title != "" && desc != "") {
            if (json != null) {
                val gson = Gson()
                val turnsType = object : TypeToken<ArrayList<ItemsObject>>() {}.type
                var turns: ArrayList<ItemsObject> = gson.fromJson(json, turnsType)
                items.addAll(turns)
            }
            items.add(ItemsObject(title, desc))
            val jsonString = Gson().toJson(items)
            sharedPreferences.edit().putString("items", jsonString).apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


    fun deleteButton(view: View) {


    }
}

