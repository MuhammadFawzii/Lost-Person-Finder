package com.example.lostpeoplefinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat


class HomeActivity : AppCompatActivity(),OnItemClickListener {
    lateinit var missingRv:RecyclerView
    lateinit var foundRv:RecyclerView
    lateinit var adapter:CommonAdapter
    lateinit var searchview:SearchView
    lateinit var reportFindingButton:Button
    lateinit var reportMissingButton:Button
    lateinit var backBtn:ImageView


    //    lateinit var filterBtn:ImageView
//    lateinit var missingBtn:Button
//    lateinit var findingBtn:Button
//    lateinit var searchIcon: ImageView
//    lateinit var  textView :TextView
//    private var isEditTextFocused = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        missingRv=findViewById(R.id.messingRecyclerView);
        foundRv=findViewById(R.id.findingRecyclerView);
        //searchview=findViewById<SearchView>(R.id.searchView)
        //filterBtn=findViewById(R.id.btn_filter)
        //searchview=findViewById(R.id.searchView)
        reportFindingButton=findViewById(R.id.reportFindingButton)
        reportMissingButton=findViewById(R.id.reportMissingButton)
        backBtn=findViewById(R.id.logoutButton)

        //filterBtn=findViewById(R.id.btn_filter)
        //textView=searchview.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
        // Set query hint

//        searchview.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                // Handle the query submission here
//                handleQuery(query)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                // Handle the text change here
//                return false
//            }
//        })


        missingRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        foundRv.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //searchIcon = searchview.findViewById(androidx.appcompat.R.id.search_mag_icon)

//        searchIcon.setOnClickListener {
//            // Handle click event for the search icon here
//            Toast.makeText(this, "Search icon clicked!", Toast.LENGTH_SHORT).show()
//        }

//        searchview.setOnFocusChangeListener { v, hasFocus ->
//            isEditTextFocused = hasFocus
//            if(hasFocus){
//                textView.setTextColor(ContextCompat.getColor(this, android.R.color.black))
//            }
//        }

        adapter = CommonAdapter(this, initList());
        missingRv.adapter = adapter ;

        adapter=CommonAdapter(this,initList("Found!!"))
        foundRv.adapter=adapter



        reportFindingButton.setOnClickListener {
            val intent= Intent(this,DetailsActivity1::class.java)
            intent.putExtra("report","1")
            startActivity(intent)
        }

        reportMissingButton.setOnClickListener {
            val intent= Intent(this,DetailsActivity1::class.java)
            intent.putExtra("report","2")
            startActivity(intent)
        }

        backBtn.setOnClickListener(View.OnClickListener {
            clearUserSession()
            // Navigate to login screen
            navigateToLoginScreen()
        })

    }

    private fun handleQuery(query: String) {
        // Here, you can perform actions based on the entered query
        // For example, you can filter a list or perform a search operation
        Toast.makeText(this, "Query: $query", Toast.LENGTH_SHORT).show()
    }

    fun initList(header:String="MISSNG!!"): ArrayList<PersonModel> {
        val personList = ArrayList<PersonModel>()

        // Add items to the list
        personList.add(PersonModel(header, R.drawable.m1, "Maria Doe", "Age:35 | Red Head | blue Eyes | Height: 167 |Weight: 120.6 lbs"))
        personList.add(PersonModel(header, R.drawable.m2, "Jane Smith", "Age:42 | black Head | Green Eyes | Height: 187 |Weight: 170.6 lbs"))
        personList.add(PersonModel(header, R.drawable.m3, "Alice Johnson", "Age:29 | brown Head | black Eyes | Height: 180 |Weight: 150.6 lbs"))
        return personList
    }


//    override fun onResume() {
//        super.onResume()
//        isEditTextFocused = false
//    }

    override fun onBackPressed() {
        if (searchview.hasFocus()) {
            searchview.clearFocus()
        } else {
            super.onBackPressed()
        }
    }

    private fun clearUserSession() {
     var rm=RememberMeHandler.getInstance(this)
      rm.clearRememberMeSession()
    }

    private fun navigateToLoginScreen() {
        // Start LoginActivity to allow the user to log in again
        val intent = Intent(this, LoginPageActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity to prevent going back to it after logout
    }

    override fun onItemClick(Item: OutputModel) {
        val intent = Intent(this, PersonDetailsActivity::class.java)
        intent.putExtra("image", Item.img)
        intent.putExtra("name", Item.personName.toString())
        intent.putExtra("age", Item.personAge.toString())
        intent.putExtra("gender", Item.personGender.toString())
        intent.putExtra("date", Item.last_date.toString())
        intent.putExtra("location", Item.personLastLocation.toString())
        startActivity(intent)
        finish()

    }
}