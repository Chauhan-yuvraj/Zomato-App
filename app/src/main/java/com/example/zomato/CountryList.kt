package com.example.zomato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import java.text.FieldPosition

class CountryList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        // Access the string array from string.xml
        val  countryArray = resources.getStringArray(R.array.country)

        // Use ArrayAdapter and define array
        val arrayAdapter : ArrayAdapter<String> = ArrayAdapter(this , android.R.layout.simple_list_item_1 , countryArray)

        // Access the ListView from the XML file
        val mlistView = findViewById<ListView>(R.id.countryList)

        // Set the ArrayAdapter to the ListView
        mlistView.adapter = arrayAdapter

        // show toast when item is clicked
        mlistView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                // value of item is clicked
                val itemValue = mlistView.getItemAtPosition(position) as String
                val selectedItem = countryArray[position]
                val resultIntent = Intent()

                resultIntent.putExtra("SelectedItem",selectedItem)
                setResult(RESULT_OK , resultIntent)
                finish()
            }
    }
}