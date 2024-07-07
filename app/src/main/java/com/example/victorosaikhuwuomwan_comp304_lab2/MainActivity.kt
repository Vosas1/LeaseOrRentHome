package com.example.victorosaikhuwuomwan_comp304_lab2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterButton: Button = findViewById(R.id.buttonEnter)
        enterButton.setOnClickListener {
            val intent = Intent(this, SelectHomeTypeActivity::class.java)
            startActivity(intent)
        }
    }
}