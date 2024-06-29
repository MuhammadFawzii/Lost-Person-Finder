package com.example.lostpeoplefinder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lostpeoplefinder.API.RetrofitClient
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportsFragment : Fragment(),OnItemClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reort, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reportsRecycler = view.findViewById<RecyclerView>(R.id.reportRecyclerView)
        val noReports = view.findViewById<TextView>(R.id.noReports)
        var reportList: ArrayList<Person> = ArrayList()

        val call = RetrofitClient.instance.getUserReports(20)
        call.enqueue(object : Callback<Map<String, Person>> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<Map<String, Person>>,
                response: Response<Map<String, Person>>
            ) {
                if (response.isSuccessful) {
                    val lostPeople = response.body()

                    reportList = ArrayList(lostPeople?.values)
                    //Toast.makeText(this@HomeActivity, personList.toString(), Toast.LENGTH_SHORT).show()
                    // Handle the response here
                    Log.d("rayeq", "Lost people: $lostPeople")
                    if (reportList.size == 0) {
                        noReports.visibility = View.VISIBLE
                        reportsRecycler.visibility = View.GONE
                    } else {
                        val reportAdapter: ReportsAdapter =
                            ReportsAdapter(this@ReportsFragment, requireContext(), reportList)
                        reportsRecycler.adapter = reportAdapter
                        reportsRecycler.layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )

                    }
//                    adapter = CommonAdapter(this@HomeActivity,this@HomeActivity, lostList)
//                    missingRv.adapter = adapter
                    //Toast.makeText(this@HomeActivity, lostPeople.toString(), Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                    Toast.makeText(
                        requireContext(),
                        "Error: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

            override fun onFailure(call: Call<Map<String, Person>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()

            }
        })
    }

    override fun onItemClick(Item: Person) {
        val intent = Intent(activity, PersonDetailsActivity::class.java)
        intent.putExtra("image", Item.image_url)
        intent.putExtra("name", Item.person_name)
        intent.putExtra("age", Item.age)
        intent.putExtra("gender", Item.gender)
        intent.putExtra("date", Item.date_of_lost)
        intent.putExtra("location", Item.lat+Item.lng)
        intent.putExtra("city",Item.city)
       startActivity(intent)
    }
}

