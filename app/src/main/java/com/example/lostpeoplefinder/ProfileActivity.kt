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
import androidx.appcompat.app.AlertDialog
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
    lateinit var curUser:User
    lateinit var o:RememberHandler


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
        o=RememberHandler.getInstance(this)

        Log.d("profileTAG", o.getUserId().toString())
        reflectUserData()

        editBtn.setOnClickListener(){
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
        notify.setOnClickListener(){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        back.setOnClickListener(){
            finish()

        }
        logout.setOnClickListener(){
            //show dialoug
            //clear shared prefrence
            showLogoutConfirmationDialog()
        }




    }
    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to log out?")
            .setPositiveButton("Logout") { dialog, which ->
                logoutUser()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun logoutUser() {
      o.clearUserData()
      o.clearUserLocation()
       startActivity(Intent(this@ProfileActivity, LoginPageActivity::class.java))
//        val call = RetrofitClient.instance.logoutUser()
//
//            call.enqueue(object : Callback<ApiResponse> {
//            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                if (response.isSuccessful) {
//                    // Handle successful logout
//                    Toast.makeText(this@ProfileActivity, "Logged out successfully", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this@ProfileActivity, LoginPageActivity::class.java)
//                    startActivity(intent)
//                    // Navigate to login screen or perform any cleanup
//                } else {
//                    // Handle unsuccessful logout
//                    Toast.makeText(this@ProfileActivity, "Failed to logout", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                // Handle failure to make the API call
//                Toast.makeText(this@ProfileActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })

    }

    override fun onResume() {
        super.onResume()
        Log.d("profileTAG","iam back after update with id : "+o.getUserId().toString())
        reflectUserData()


    }
    fun reflectUserData(){

                        userName.setText(o.getUserName().toString())
                        userCity.setText(o.getUserCity().toString())
                        userMobile.setText(o.getUserPhone().toString())
                        userEmail.setText(o.getUserEmail().toString())
                        userLocation.setText(o.getUserLocation().toString())
    }
}