package com.example.victorosaikhuwuomwan_comp304_lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        val selectedHomesTextView: TextView = findViewById(R.id.textViewSelectedHomes)
        val proceedButton: Button = findViewById(R.id.buttonProceedToPayment)

        // Get selected homes data from intent
        val selectedHomes = intent.getStringArrayListExtra("selectedHomes")
        val homeType = intent.getStringExtra("homeType")
        selectedHomesTextView.text = selectedHomes?.joinToString("\n")

        proceedButton.setOnClickListener {
            val intent = Intent(this, PaymentOptionsActivity::class.java)
            intent.putStringArrayListExtra("selectedHomes", selectedHomes)
            intent.putExtra("homeType", homeType)  // Pass the home type
            startActivity(intent)
        }
    }
}


