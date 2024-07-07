package com.example.victorosaikhuwuomwan_comp304_lab2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class CustomerInfoActivity : AppCompatActivity() {
    private lateinit var fullNameEditText: EditText
    private lateinit var cardNumberEditText: EditText
    private lateinit var favoriteSportEditText: EditText
    private lateinit var favoriteTeamEditText: EditText
    private lateinit var favoriteFoodEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var homeType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_info)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Retrieve the selected home type from the intent
        homeType = intent.getStringExtra("homeType") ?: "Unknown"
        supportActionBar?.title = homeType

        fullNameEditText = findViewById(R.id.editTextFullName)
        cardNumberEditText = findViewById(R.id.editTextCardNumber)
        favoriteSportEditText = findViewById(R.id.editTextFavoriteSport)
        favoriteTeamEditText = findViewById(R.id.editTextFavoriteTeam)
        favoriteFoodEditText = findViewById(R.id.editTextFavoriteFood)
        submitButton = findViewById(R.id.buttonSubmit)

        submitButton.setOnClickListener {
            // Get user input
            val fullName = fullNameEditText.text.toString()
            val cardNumber = cardNumberEditText.text.toString()
            val favoriteSport = favoriteSportEditText.text.toString()
            val favoriteTeam = favoriteTeamEditText.text.toString()
            val favoriteFood = favoriteFoodEditText.text.toString()

            // Validate input
            if (fullName.isEmpty() || cardNumber.isEmpty() || favoriteSport.isEmpty() || favoriteTeam.isEmpty() || favoriteFood.isEmpty()) {
                // Show error message
                return@setOnClickListener
            }

            val userData = "Full Name: $fullName\nCard Number: $cardNumber\nFavorite Sport: $favoriteSport\nFavorite Team: $favoriteTeam\nFavorite Food: $favoriteFood"
            Log.d("User Data", userData)


        }
    }
}