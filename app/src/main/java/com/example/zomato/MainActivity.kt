package com.example.zomato

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val selectedItem = data?.getStringExtra("SelectedItem")

                // Update the button text with the selected item
                val countryBtn = findViewById<Button>(R.id.countryBtn)
                countryBtn.text = selectedItem
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val countryBtn = findViewById<Button>(R.id.countryBtn)

        countryBtn.setOnClickListener {
            val intent = Intent(this, CountryList::class.java)
            startForResult.launch(intent)
        }
    }
}
