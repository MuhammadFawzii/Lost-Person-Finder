package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast

class DetailsActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details5)

        val name=intent.getStringExtra("name")
        val age=intent.getStringExtra("age")
        val gender=intent.getStringExtra("gender")
        val date=intent.getStringExtra("date")
        val lang=intent.getStringExtra("lang")
        val lat=intent.getStringExtra("lat")
        val btn_next5=findViewById<Button>(R.id.bt_next5)
        val btn_previous5=findViewById<Button>(R.id.bt_previous5)
        val eNumber=findViewById<EditText>(R.id.number)
        val eEmail=findViewById<EditText>(R.id.email)
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val phoneNumberPattern = "\\d{11}"
        btn_next5.setOnClickListener {
            val number=eNumber.text.toString()
            val email=eEmail.text.toString()
            val isEmailValid = email.matches(emailPattern.toRegex())
            val isPhoneNumberValid = number.matches(phoneNumberPattern.toRegex())
            if (!isEmailValid) {
                Toast.makeText(this, "Email not valid \n Should be like example@gmail.com", Toast.LENGTH_LONG).show()
            }
            if (!isPhoneNumberValid) {
                Toast.makeText(this, "Phone Number not valid \n Should be 11 char", Toast.LENGTH_LONG).show()
            }
            if (isEmailValid && isPhoneNumberValid) {
                val intent=Intent(this,DetailsActivity5::class.java)
                intent.putExtra("name", name)
                intent.putExtra("gender", gender)
                intent.putExtra("age", age)
                intent.putExtra("date",date)
                intent.putExtra("number",number)
                intent.putExtra("email",email)
                intent.putExtra("lang", lang)
                intent.putExtra("lat",lat)
                startActivity(intent)

            }
        }
        btn_previous5.setOnClickListener {
            startActivity(Intent(this,DetailsActivity3::class.java))
        }

    }
}