package com.example.victorosaikhuwuomwan_comp304_lab2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.widget.Toast

class PaymentOptionsActivity : AppCompatActivity() {
    private lateinit var homeType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_options)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Retrieve the selected home type from the intent
        homeType = intent.getStringExtra("homeType") ?: "Unknown"
        supportActionBar?.title = homeType

        val radioGroupPaymentOptions: RadioGroup = findViewById(R.id.radioGroupPaymentOptions)
        val editTextCardNumber: EditText = findViewById(R.id.editTextCardNumber)
        val buttonPayCash: Button = findViewById(R.id.buttonPayCash)
        val buttonProceedToPayment: Button = findViewById(R.id.buttonProceedToPayment)

        // Hide the card number input initially
        editTextCardNumber.visibility = View.GONE

        // Add listener to RadioGroup to show/hide card number input based on selected payment option
        radioGroupPaymentOptions.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButtonCash -> {
                    editTextCardNumber.visibility = View.GONE
                }
                R.id.radioButtonCreditCard, R.id.radioButtonDebitCard -> {
                    editTextCardNumber.visibility = View.VISIBLE
                }
            }
        }

        buttonPayCash.setOnClickListener {
            // Handle payment logic for cash
            Toast.makeText(this, "Payment with Cash", Toast.LENGTH_SHORT).show()
        }

        buttonProceedToPayment.setOnClickListener {
            val selectedOption = radioGroupPaymentOptions.checkedRadioButtonId
            val cardNumber = editTextCardNumber.text.toString()

            when (selectedOption) {
                R.id.radioButtonCash -> {
                    // Handle payment logic for cash
                    Toast.makeText(this, "Payment with Cash", Toast.LENGTH_SHORT).show()
                }
                R.id.radioButtonCreditCard -> {
                    // Validate card number for credit card
                    if (cardNumber.isEmpty()) {
                        Toast.makeText(this, "Please enter your credit card number", Toast.LENGTH_SHORT).show()
                    } else {
                        // Proceed to the next step in payment process
                        navigateToNextStep()
                    }
                }
                R.id.radioButtonDebitCard -> {
                    // Validate card number for debit card
                    if (cardNumber.isEmpty()) {
                        Toast.makeText(this, "Please enter your debit card number", Toast.LENGTH_SHORT).show()
                    } else {
                        // Proceed to the next step in payment process
                        navigateToNextStep()
                    }
                }
                else -> {
                    Toast.makeText(this, "Please select a payment option", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToNextStep() {
        val intent = Intent(this, CustomerInfoActivity::class.java)
        intent.putExtra("homeType", homeType)  // Pass the home type to the next activity
        startActivity(intent)
    }
}



