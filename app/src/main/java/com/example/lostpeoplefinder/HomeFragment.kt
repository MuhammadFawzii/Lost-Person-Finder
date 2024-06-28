package com.example.lostpeoplefinder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lostpeoplefinder.API.RetrofitClient
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(),OnItemClickListener {
    lateinit var missingRv: RecyclerView
    lateinit var foundRv: RecyclerView
    lateinit var adapter:CommonAdapter
    lateinit var searchview: SearchView
    lateinit var reportFindingButton: Button
    lateinit var reportMissingButton: Button
    lateinit var backBtn: ImageView
    private lateinit var mShimmerViewContainer: ShimmerFrameLayout
    private lateinit var mShimmerViewContainer2: ShimmerFrameLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var lostList: ArrayList<Person>
        var foundList: ArrayList<Person>
        lostList = ArrayList()
        foundList = ArrayList()
        missingRv = view.findViewById(R.id.messingRecyclerView)
        foundRv = view.findViewById(R.id.findingRecyclerView)
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container)
        mShimmerViewContainer2 = view.findViewById(R.id.shimmer_view_container2)
        //searchview=findViewById<SearchView>(R.id.searchView)
        //filterBtn=findViewById(R.id.btn_filter)
        //searchview=findViewById(R.id.searchView)
        reportFindingButton = view.findViewById(R.id.reportFindingButton)
        reportMissingButton = view.findViewById(R.id.reportMissingButton)
        backBtn = view.findViewById(R.id.logoutButton)
        // FirebaseApp.initializeApp(this)


        val call = RetrofitClient.instance.getLostPeople()
        call.enqueue(object : Callback<Map<String, Person>> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<Map<String, Person>>,
                response: Response<Map<String, Person>>
            ) {
                if (response.isSuccessful) {
                    val lostPeople = response.body()

                    lostList = ArrayList(lostPeople?.values)
                    //Toast.makeText(this@HomeActivity, personList.toString(), Toast.LENGTH_SHORT).show()
                    // Handle the response here
                    Log.d("MainActivity", "Lost people: $lostPeople")
                    Log.d("MainActivity", "Lost: $lostList")
                    adapter = CommonAdapter(this@HomeFragment,requireContext(), lostList,"Missing")
                    missingRv.adapter = adapter
                    //Toast.makeText(this@HomeActivity, lostPeople.toString(), Toast.LENGTH_SHORT).show()
                }

                else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                    Toast.makeText(requireContext(), "Error: ${response.message()}", Toast.LENGTH_SHORT).show()

                }
                mShimmerViewContainer.stopShimmer()
                mShimmerViewContainer.visibility=View.GONE

                mShimmerViewContainer2.stopShimmer()
                mShimmerViewContainer2.visibility=View.GONE
            }

            override fun onFailure(call: Call<Map<String, Person>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()

            }
        })

        val call2 = RetrofitClient.instance.getFoundPeople()
        call2.enqueue(object : Callback<Map<String, Person>> {
            override fun onResponse(
                call: Call<Map<String, Person>>,
                response: Response<Map<String, Person>>
            ) {
                if (response.isSuccessful) {
                    val lostPeople = response.body()

                    foundList = ArrayList(lostPeople?.values)
                    //Toast.makeText(this@HomeActivity, personList.toString(), Toast.LENGTH_SHORT).show()
                    // Handle the response here
                    Log.d("MainActivity", "Lost people: $lostPeople")
                    Log.d("MainActivity", "Lost: $foundList")
                    adapter=CommonAdapter(this@HomeFragment,requireContext(),foundList,"Founded")
                    foundRv.adapter=adapter
                    //Toast.makeText(this@HomeActivity, lostPeople.toString(), Toast.LENGTH_SHORT).show()
                }

                else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                    Toast.makeText(requireContext(), "Error: ${response.message()}", Toast.LENGTH_SHORT).show()

                }
                mShimmerViewContainer.stopShimmer()
                mShimmerViewContainer.visibility=View.GONE

                mShimmerViewContainer2.stopShimmer()
                mShimmerViewContainer2.visibility=View.GONE
            }

            override fun onFailure(call: Call<Map<String, Person>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()

            }
        })


        missingRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        foundRv.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        reportFindingButton.setOnClickListener {
            val intent= Intent(activity,DetailsActivity1::class.java)
            intent.putExtra("report","1")
            startActivity(intent)
        }

        reportMissingButton.setOnClickListener {
            val intent= Intent(activity,DetailsActivity1::class.java)
            intent.putExtra("report","2")
            startActivity(intent)
        }

        backBtn.setOnClickListener(View.OnClickListener {
            clearUserSession()
            // Navigate to login screen
            navigateToLoginScreen()
        })
    }

//    private fun handleQuery(query: String) {
//        // Here, you can perform actions based on the entered query
//        // For example, you can filter a list or perform a search operation
//        Toast.makeText(this, "Query: $query", Toast.LENGTH_SHORT).show()
//    }


//    override fun onResume() {
//        super.onResume()
//        isEditTextFocused = false
//    }

    private fun clearUserSession() {
        var rm=RememberMeHandler.getInstance(requireContext())
        rm.clearRememberMeSession()
    }

    private fun navigateToLoginScreen() {
        // Start LoginActivity to allow the user to log in again
        val intent = Intent(activity, LoginPageActivity::class.java)
        startActivity(intent)
       // finish() // Finish the current activity to prevent going back to it after logout
    }


    override fun onItemClick(Item: Person) {
        val intent = Intent(activity, PersonDetailsActivity::class.java)
        intent.putExtra("image", Item.image_url)
        intent.putExtra("name", Item.person_name.toString())
        intent.putExtra("age", Item.age.toString())
        intent.putExtra("gender", Item.gender.toString())
        intent.putExtra("date", Item.date_of_lost.toString())
        intent.putExtra("location", Item.lat.toString()+Item.lng.toString())
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        mShimmerViewContainer.startShimmer()
        mShimmerViewContainer2.startShimmer()
    }

    override fun onPause() {
        mShimmerViewContainer.stopShimmer()
        mShimmerViewContainer2.stopShimmer()
        super.onPause()
    }
}