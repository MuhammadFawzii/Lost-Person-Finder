package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class PersonDetailsActivity : AppCompatActivity() {
    lateinit var img:ImageView
    lateinit var name: TextView
    lateinit var age: TextView
    lateinit var gender: TextView
    lateinit var location: TextView
    lateinit var date: TextView
    lateinit var personitem:OutputModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        img=findViewById(R.id.imageViewPersonDetails)
        name=findViewById(R.id.personNameTextView)
        age=findViewById(R.id.personAgeTextView)
        gender=findViewById(R.id.personGenderTextView)
        location=findViewById(R.id.personLocationTextView)
        date=findViewById(R.id.personDateTextView)


        img.setImageResource(intent.getIntExtra("image",0))
        name.text= intent.getStringExtra("name")
        age.text=intent.getStringExtra("age")
        gender.text=intent.getStringExtra("gender")
        location.text=intent.getStringExtra("location")
        date.text=intent.getStringExtra("date")






    }

}