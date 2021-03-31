package com.example.noteapp

import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    var items = ArrayList<ItemsObject>()
    var savedTitle: String? = null
    var savedDesc: String? = null
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        sharedPreferences = this.getSharedPreferences("com.example.noteapp", MODE_PRIVATE)

        savedTitle = intent.getStringExtra("title")
        savedDesc = intent.getStringExtra("desc")

        editTextTextTitle.setText(savedTitle)
        editTextTextDescription.setText(savedDesc)

    }

    fun saveButton(view: View) {

        val json = sharedPreferences.getString("items", null)
        val title = editTextTextTitle.text.toString()
        val desc = editTextTextDescription.text.toString()


        if (savedTitle != null && savedDesc != null) {
            if (json != null) {
                val gson = Gson()
                val turnsType = object : TypeToken<ArrayList<ItemsObject>>() {}.type
                val turns: ArrayList<ItemsObject> = gson.fromJson(json, turnsType)
                turns.forEach { _object ->
                    if((_object.title == savedTitle && _object.description == savedDesc)){
                        items.add(ItemsObject(title, desc))
                    } else {
                        items.add(_object)
                    }
                }

                val jsonString = Gson().toJson(items)
                sharedPreferences.edit().putString("items", jsonString).apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        } else if (title != "" && desc != "") {
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

