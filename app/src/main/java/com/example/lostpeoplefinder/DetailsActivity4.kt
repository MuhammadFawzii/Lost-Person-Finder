package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailsActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details4)
        val btn_next4=findViewById<Button>(R.id.bt_next4)
        val btn_previous4=findViewById<Button>(R.id.btn_previous4)
        btn_next4.setOnClickListener {
            startActivity(Intent(this,DetailsActivity5::class.java))
        }
        btn_previous4.setOnClickListener {
            startActivity(Intent(this,DetailsActivity3::class.java))
        }
        val enterLocationTextView = findViewById<TextView>(R.id.tv_enter_location)
        enterLocationTextView.setOnClickListener { // Open a new page or handle Google Maps here
            // For example, you can start a new activity
            startActivity(Intent(this@DetailsActivity4, MapActivity::class.java))
        }
    }
}