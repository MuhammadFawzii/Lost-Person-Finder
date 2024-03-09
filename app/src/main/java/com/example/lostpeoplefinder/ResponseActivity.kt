package com.example.lostpeoplefinder

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class ResponseActivity : AppCompatActivity(),OnItemClickListener {
    private lateinit var topLeftTextView: TextView
    private lateinit var middleTextView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:ResponseAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_response)
        val matchedItems = intent.getSerializableExtra("final") as ArrayList<Person>
        // Initialize views
        topLeftTextView = findViewById(R.id.topLeftTextView)
        middleTextView = findViewById(R.id.middleTextView)
        recyclerView = findViewById(R.id.recyclerView)

        // Simulated list of matched items


        // Check if the list of matched items is empty
        if (matchedItems.isEmpty()) {
            // Show the middleTextView and hide the rest
            topLeftTextView.visibility = View.GONE
            recyclerView.visibility = View.GONE
            middleTextView.visibility = View.VISIBLE

        } else {



            // Hide the middleTextView and show the rest
            topLeftTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.VISIBLE
            middleTextView.visibility = View.GONE

            // Initialize and set up your RecyclerView adapter and layout manager here
            // Example:
            adapter = ResponseAdapter(this,this, matchedItems)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)



        }
    }
        fun getMatchedlist(isfull:Boolean = true):ArrayList<OutputModel>{
            val personList = ArrayList<OutputModel>()
            if(!isfull)return personList;//return empty list to show message

            val outputModel = OutputModel(
                img = R.drawable.miss, // Assuming you have an image resource ID
                personName = "John Doe",
                personAge = 30,
                personGender = "Male",
                last_date = Date(), // Current date
                personLastLocation = Location("provider").apply {
                    latitude = 88.456 // Replace with actual latitude
                    longitude = 54.789 // Replace with actual longitude
                })

            // Add items to the list
            personList.add(outputModel)
            personList.add(outputModel)
            personList.add(outputModel)
            personList.add(outputModel)
            personList.add(outputModel)
            return personList

        }


    override fun onItemClick(Item: Person) {
      val intent = Intent(this, PersonDetailsActivity::class.java)
        intent.putExtra("image", Item.image_url)
        intent.putExtra("name", Item.person_name.toString())
        intent.putExtra("age", Item.age.toString())
        intent.putExtra("gender", Item.gender.toString())
        intent.putExtra("date", Item.date_of_lost.toString())
        intent.putExtra("location", Item.lat.toString()+Item.lng.toString())
        startActivity(intent)
    }


}



