package com.example.lostpeoplefinder

import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.util.*

class PersonDetailsActivity : AppCompatActivity() {
    lateinit var img:ImageView
    lateinit var name: TextView
    lateinit var age: TextView
    lateinit var gender: TextView
    lateinit var location: TextView
    lateinit var date: TextView
    private lateinit var geocoder:Geocoder



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        img=findViewById(R.id.imageViewPersonDetails)
        name=findViewById(R.id.personNameTextView)
        age=findViewById(R.id.personAgeTextView)
        gender=findViewById(R.id.personGenderTextView)
        location=findViewById(R.id.personLocationTextView)
        date=findViewById(R.id.personDateTextView)
        geocoder = Geocoder(this, Locale.getDefault())
        Toast.makeText(this, convertLatLongToLocation(29.95375640,31.53700030), Toast.LENGTH_SHORT).show()
        val ig=intent.getStringExtra("image")
        Glide.with(this)
            .load(ig)
            .into(img)
        name.text = intent.getStringExtra("name")
        age.text = intent.getStringExtra("age")
        val x=intent.getStringExtra("gender")
        var gen:String = if(x=="1")
            "Male"
        else
            "female"
        gender.text = gen
        location.text = intent.getStringExtra("location")
        date.text = intent.getStringExtra("date")






    }
     fun convertLatLongToLocation(latitude: Double, longitude: Double): String {

        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses?.isEmpty()==false) {
                val address = addresses[0]
                // Build the address string
                val sb = StringBuilder()
                /*for (i in 0..address.maxAddressLineIndex) {
                    sb.append(address.getAddressLine(i)).append("\n")
                }*/
                sb.append(address.locality?:address.getAddressLine(0))
                return sb.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "Location not found"
    }

}