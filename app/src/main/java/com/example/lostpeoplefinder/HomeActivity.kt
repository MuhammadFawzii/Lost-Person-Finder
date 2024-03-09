package com.example.lostpeoplefinder

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat


class HomeActivity : AppCompatActivity() {
    lateinit var missingRv:RecyclerView
    lateinit var foundRv:RecyclerView
    lateinit var adapter:CommonAdapter
    lateinit var missingBtn:Button
    lateinit var findingBtn:Button
    lateinit var searchview:SearchView
    lateinit var filterBtn:ImageView
    lateinit var searchIcon: ImageView
    lateinit var  textView :TextView
    private var isEditTextFocused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        missingRv=findViewById(R.id.messingRecyclerView);
        foundRv=findViewById(R.id.findingRecyclerView);
        missingBtn=findViewById(R.id.reportMissingButton)
        findingBtn=findViewById(R.id.reportFindingButton)

        //searchview=findViewById(R.id.searchView)
        //filterBtn=findViewById(R.id.btn_filter)
        //searchIcon = searchview.findViewById(androidx.appcompat.R.id.search_mag_icon)
        //filterBtn=findViewById(R.id.btn_filter)
        //textView=searchview.findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
        //        searchIcon.setOnClickListener {
//            // Handle click event for the search icon here
//            Toast.makeText(this, "Search icon clicked!", Toast.LENGTH_SHORT).show()
//        }


        missingRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        foundRv.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



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
        personList.add(PersonModel(header, R.drawable.miss, "Maria Doe", "Age:35 | Red Head | blue Eyes | Height: 167 |Weight: 120.6 lbs"))
        personList.add(PersonModel(header, R.drawable.miss, "Jane Smith", "Age:42 | black Head | Green Eyes | Height: 187 |Weight: 170.6 lbs"))
        personList.add(PersonModel(header, R.drawable.miss, "Alice Johnson", "Age:29 | brown Head | black Eyes | Height: 180 |Weight: 150.6 lbs"))
        return personList
    }
    /*override fun onResume() {
        super.onResume()
        isEditTextFocused = false
    }*/

    /*override fun onBackPressed() {
        if (searchview.hasFocus()) {
            searchview.clearFocus()
        } else {
            super.onBackPressed()
        }
    }*/
}