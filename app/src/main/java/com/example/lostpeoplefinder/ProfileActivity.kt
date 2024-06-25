package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.lostpeoplefinder.API.RetrofitClient
import com.example.yourapp.RememberHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileActivity : AppCompatActivity() {
    lateinit var back:ImageView
    lateinit var notify:ImageView
    lateinit var logout:ImageView
    lateinit var nameView:View
    lateinit var addressView:View
    lateinit var mobileView:View
    lateinit var emailView:View
    lateinit var editBtn:Button
    lateinit var userName:TextView
    lateinit var userEmail:TextView
    lateinit var userMobile:TextView
    lateinit var userCity:TextView
    lateinit var userLocation:TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        back=findViewById(R.id.back_btn)
        notify=findViewById(R.id.notify_btn)
        logout=findViewById(R.id.logout_btn)
        nameView=findViewById(R.id.img)
        addressView=findViewById(R.id.address_section)
        mobileView=findViewById(R.id.mobile_section)
        emailView=findViewById(R.id.email_section)
        editBtn=findViewById(R.id.edit_btn)
        userName=nameView.findViewById(R.id.user_name)
        userCity=addressView.findViewById(R.id.user_city)
        userLocation=addressView.findViewById(R.id.user_location)
        userEmail=emailView.findViewById(R.id.user_email)
        userMobile=mobileView.findViewById(R.id.user_mobile)
        var curUser=User(
            id = -1,
            username = "na",
            email = "na",
            phone_number = "na",
            longitude = "na",
            latitude = "na",
            city = "na",
            notification_enabled = true,
            token = "na"
        )

        val userLoggingid = RememberHandler.getInstance(this).getUserId()

// Log the user ID for debugging purposes
        Log.d("profileTAG", userLoggingid.toString())

        // Example API call to get user by email
        val call = RetrofitClient.instance.getUser(userLoggingid ?: -1) // Use a default value if user ID is null

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        // Update the UI with user details
                        curUser=user
                        userName.setText(user.username)
                        userCity.setText(user.city)
                        userMobile.setText(user.phone_number)
                        userEmail.setText(user.email)
                        userLocation.setText("Nasir City\n Mustafa Elnahas Street") // Static text, adjust as needed
                        Log.d("profileTAG", "User details updated successfully")
                    } else {
                        Log.d("profileTAG", "User object is null")
                    }
                } else {
                    Log.d("profileTAG", "Response failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("profileTAG", "Failed to fetch user details: ${t.message}")
            }
        })
        editBtn.setOnClickListener(){
            /*val intent = Intent(this, EditProfileActivity::class.java).apply {
                putExtra("name", userName.text)
                putExtra("city", userCity.text)
                putExtra("loc", userLocation.text)
                putExtra("mob", userMobile.text)
            }*/

            val intent = Intent(this, EditProfileActivity::class.java).apply {
                Log.d("bkr",curUser.username.toString())
                putExtra("user", curUser)
                putExtra("loc",userLocation.text)
            }
            startActivity(intent)

        }






    }
}