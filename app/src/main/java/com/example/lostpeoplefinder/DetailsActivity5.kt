package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class DetailsActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details6)
        val note=findViewById<EditText>(R.id.notes)
        val name=intent.getStringExtra("name")
        val age=intent.getStringExtra("age")
        val gender=intent.getStringExtra("gender")
        val date=intent.getStringExtra("date")
        val number=intent.getStringExtra("number")
        val email=intent.getStringExtra("email")
        val lang=intent.getStringExtra("lang")
        val lat=intent.getStringExtra("lat")


        val btn_next=findViewById<Button>(R.id.bt_next6)
        val btn_previous=findViewById<Button>(R.id.bt_previous6)
        btn_next.setOnClickListener {
            val note=note.text.toString()
            val intent=Intent(this,DetailsActivity6::class.java)
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("date",date)
            intent.putExtra("number",number)
            intent.putExtra("email",email)
            intent.putExtra("note",note)
            intent.putExtra("lang", lang)
            intent.putExtra("lat",lat)
            startActivity(intent)
            startActivity(Intent(this,DetailsActivity6::class.java))
        }
        btn_previous.setOnClickListener {
            startActivity(Intent(this,DetailsActivity4::class.java))
        }
    }
}