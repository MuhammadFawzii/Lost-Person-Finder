package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailsCompleteActivity8 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details8_complete)
        val back=findViewById<Button>(R.id.bt_goToDashboard)
        back.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))

        }
    }
}