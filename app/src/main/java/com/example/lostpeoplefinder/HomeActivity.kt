package com.example.lostpeoplefinder

import android.os.Bundle
import android.widget.*
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    lateinit var missingRv:RecyclerView
    lateinit var foundRv:RecyclerView
    lateinit var adapter:CommonAdapter
    lateinit var searchview:SearchView
    lateinit var filterBtn:ImageView
    lateinit var missingBtn:Button
    lateinit var findingBtn:Button
    private var isEditTextFocused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        missingRv=findViewById(R.id.messingRecyclerView);
        foundRv=findViewById(R.id.findingRecyclerView);
        searchview=findViewById<SearchView>(R.id.searchView)
        filterBtn=findViewById(R.id.btn_filter)


        // Set query hint
        searchview.queryHint = "Search Here ..."

        // Set listener to handle text changes
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // This method is called when the user submits the query (e.g., presses Enter)
                // Handle the query submission here
                handleQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // This method is called when the text in the search view changes
                // Handle the text change here
                return false
            }
        })
        //val layoutManager = LinearLayoutManager(this)
        missingRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        foundRv.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        searchview.setOnFocusChangeListener { v, hasFocus ->
            isEditTextFocused = hasFocus
        }

        adapter = CommonAdapter(this, initList());
        missingRv.adapter = adapter ;

        adapter=CommonAdapter(this,initList("Found!!"))
        foundRv.adapter=adapter





    }

    private fun handleQuery(query: String) {
        // Here, you can perform actions based on the entered query
        // For example, you can filter a list or perform a search operation
        Toast.makeText(this, "Query: $query", Toast.LENGTH_SHORT).show()
    }

    fun initList(header:String="MISSNG!!"): ArrayList<PersonModel> {
        val personList = ArrayList<PersonModel>()

        // Add items to the list
        personList.add(PersonModel(header, R.drawable.missing, "Maria Doe", "Age:35 | Red Head | blue Eyes | Height: 167 |Weight: 120.6 lbs"))
        personList.add(PersonModel(header, R.drawable.missing, "Jane Smith", "Age:42 | black Head | Green Eyes | Height: 187 |Weight: 170.6 lbs"))
        personList.add(PersonModel(header, R.drawable.missing, "Alice Johnson", "Age:29 | brown Head | black Eyes | Height: 180 |Weight: 150.6 lbs"))
        return personList
    }
    override fun onResume() {
        super.onResume()
        isEditTextFocused = false
    }

    override fun onBackPressed() {
        if (searchview.hasFocus()) {
            searchview.clearFocus()
        } else {
            super.onBackPressed()
        }
    }
}