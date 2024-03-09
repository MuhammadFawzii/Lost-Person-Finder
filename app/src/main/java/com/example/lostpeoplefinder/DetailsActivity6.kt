package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailsActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details6)
        val btn_next=findViewById<Button>(R.id.bt_next6)
        val btn_previous=findViewById<Button>(R.id.bt_previous6)
        btn_next.setOnClickListener {
            startActivity(Intent(this,DetailsActivity7::class.java))
        }
        btn_previous.setOnClickListener {
            startActivity(Intent(this,DetailsActivity5::class.java))
        }
    }
}