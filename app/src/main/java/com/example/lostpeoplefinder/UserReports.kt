package com.example.lostpeoplefinder

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lostpeoplefinder.API.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserReports : AppCompatActivity(),OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_reports)
        val reportsRecycler=findViewById<RecyclerView>(R.id.reportRecyclerView)
        val noReports=findViewById<TextView>(R.id.noReports)
        var reportList:ArrayList<Person> = ArrayList()

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
                    if(reportList.size==0){
                        noReports.visibility= View.VISIBLE
                        reportsRecycler.visibility=View.GONE
                    }else {
                        val reportAdapter: ReportsAdapter =
                            ReportsAdapter(this@UserReports, this@UserReports, reportList)
                        reportsRecycler.adapter = reportAdapter
                        reportsRecycler.layoutManager =
                            LinearLayoutManager(this@UserReports, LinearLayoutManager.VERTICAL, false)

                    }
//                    adapter = CommonAdapter(this@HomeActivity,this@HomeActivity, lostList)
//                    missingRv.adapter = adapter
                    //Toast.makeText(this@HomeActivity, lostPeople.toString(), Toast.LENGTH_SHORT).show()
                }

                else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                    Toast.makeText(this@UserReports, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<Map<String, Person>>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
                Toast.makeText(this@UserReports, t.message, Toast.LENGTH_SHORT).show()

            }
        })




//        reportList.add(Person("abdo","20","26/5/2002","01013513652","abdo@gmail.com","test","35.2555","30.2200","1","Cairo"))
//        reportList.add(Person("abdo","20","26/5/2002","01013513652","abdo@gmail.com","test","35.2555","30.2200","2","Cairo"))
//        reportList.add(Person("abdo","20","26/5/2002","01013513652","abdo@gmail.com","test","35.2555","30.2200","1","Cairo"))
//        reportList.add(Person("abdo","20","26/5/2002","01013513652","abdo@gmail.com","test","35.2555","30.2200","2","Cairo"))
//        reportList.add(Person("abdo","20","26/5/2002","01013513652","abdo@gmail.com","test","35.2555","30.2200","1","Cairo"))
//        reportList.add(Person("abdo","20","26/5/2002","01013513652","abdo@gmail.com","test","35.2555","30.2200","2","Cairo"))
//        reportList.add(Person("abdo","20","26/5/2002","01013513652","abdo@gmail.com","test","35.2555","30.2200","1","Cairo"))
//        reportList.add(Person("abdo","20","26/5/2002","01013513652","abdo@gmail.com","test","35.2555","30.2200","2","Cairo"))
//        if(reportList.size==0){
//            noReports.visibility= View.VISIBLE
//            reportsRecycler.visibility=View.GONE
//        }else {
//            val reportAdapter: ReportsAdapter =
//                ReportsAdapter(this@UserReports, this@UserReports, reportList)
//            reportsRecycler.adapter = reportAdapter
//            reportsRecycler.layoutManager =
//                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        }




    }
    override fun onItemClick(Item: Person) {
        val intent = Intent(this, PersonDetailsActivity::class.java)
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