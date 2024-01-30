package com.example.zomato

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner : Spinner = findViewById(R.id.spinner)
        // Create an ArrayAdapter using the string and a default spinner layout
        ArrayAdapter.createFromResource(this,R.array.country,android.R.layout.simple_spinner_item).also { adapter ->
            //Specify the layout to use when the list of choice appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply sipper to the adapter
            spinner.adapter = adapter
        }

//        Respond to the user selection
    class SpinnerActivity : Activity(), AdapterView.OnItemSelectedListener{
    override fun onItemSelected(parent: AdapterView<*>, view: View,pos: Int, id: Long) {}
    override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}