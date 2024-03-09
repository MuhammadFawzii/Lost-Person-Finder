package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailsActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details5)
        val btn_next5=findViewById<Button>(R.id.bt_next5)
        val btn_previous5=findViewById<Button>(R.id.bt_previous5)
        btn_next5.setOnClickListener {
            startActivity(Intent(this,DetailsActivity6::class.java))
        }
        btn_previous5.setOnClickListener {
            startActivity(Intent(this,DetailsActivity4::class.java))
        }
    }
}