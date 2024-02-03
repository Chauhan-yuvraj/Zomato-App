package com.example.zomato

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    // Declare database reference
    lateinit var database: DatabaseReference

    // Activity result launcher for startActivityForResult
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

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance().getReference("users")

        // Set up click listener for the country selection button
        val countryBtn = findViewById<Button>(R.id.countryBtn)
        countryBtn.setOnClickListener {
            val intent = Intent(this, CountryList::class.java)
            startForResult.launch(intent)
        }

        // Set up click listener for the continue button (login process)
        val phoneNumber = findViewById<EditText>(R.id.phoneNumber)
        val continueBtn = findViewById<Button>(R.id.continueBtn)
        continueBtn.setOnClickListener {
            val number = phoneNumber.text.toString()

            if (number.isNotEmpty()) {
                // Create a User object with the phone number
                val user = User(number)

                // Write user data to Firebase Database
                writeToDatabase(number, user)
            } else {
                Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun writeToDatabase(number: String, user: User) {
        database.child(number).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
