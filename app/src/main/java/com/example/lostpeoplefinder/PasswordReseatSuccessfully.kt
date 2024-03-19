package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PasswordReseatSuccessfully : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset_successful)
        val login:Button=findViewById(R.id.bt_go_toLogin)
        login.setOnClickListener {
            startActivity(Intent(this@PasswordReseatSuccessfully,LoginPageActivity::class.java))
        }
    }
}