package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class DetailsActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details6)
        val report_name = intent.getStringExtra("report")
        val note=findViewById<EditText>(R.id.notes)
        val name=intent.getStringExtra("name")
        val age=intent.getStringExtra("age")
        val gender=intent.getStringExtra("gender")
        val date=intent.getStringExtra("date")
        val number=intent.getStringExtra("number")
        val email=intent.getStringExtra("email")
        val lang=intent.getStringExtra("lang")
        val lat=intent.getStringExtra("lat")

        Toast.makeText(this@DetailsActivity5, report_name.toString(), Toast.LENGTH_SHORT)
            .show()
        val btn_next=findViewById<Button>(R.id.bt_next6)
        val btn_previous=findViewById<Button>(R.id.bt_previous6)
        btn_next.setOnClickListener {
            val note=note.text.toString()
            val intent1=Intent(this,DetailsActivity6::class.java)
            intent1.putExtra("name", name)
            intent1.putExtra("gender", gender)
            intent1.putExtra("age", age)
            intent1.putExtra("date",date)
            intent1.putExtra("number",number)
            intent1.putExtra("email",email)
            intent1.putExtra("note",note)
            intent1.putExtra("lang", lang)
            intent1.putExtra("lat",lat)
            intent1.putExtra("report", report_name)
            startActivity(intent1)

        }
        btn_previous.setOnClickListener {
            startActivity(Intent(this,DetailsActivity4::class.java))
        }
    }
}