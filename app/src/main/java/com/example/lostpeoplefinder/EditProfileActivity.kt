package com.example.lostpeoplefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lostpeoplefinder.API.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {

   lateinit var nameET:EditText
    lateinit var cityET:EditText
    lateinit var locationET:EditText
    lateinit var phoneET:EditText
    lateinit var saveBtn:Button
    lateinit var cancelBtn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        // Retrieve data passed via Intent extras
        //val name = intent.getStringExtra("name")
        //val city = intent.getStringExtra("city")
        //val mobile = intent.getStringExtra("mob")

         val location = intent.getStringExtra("loc")
         val receivedUser = intent.getSerializableExtra("user") as? User

        Log.d("editProfileTAG",location.toString())
        Log.d("editProfileTAG",receivedUser?.username.toString())



        // Set data into EditText fields
        nameET=findViewById(R.id.editTextName)
        cityET=findViewById(R.id.editTextCity)
        locationET=findViewById(R.id.editTextLocation)
        phoneET=findViewById(R.id.editTextMobile)
        saveBtn=findViewById(R.id.buttonSave)
        cancelBtn=findViewById(R.id.buttonCancel)



        if (receivedUser != null) {
            nameET.setText(receivedUser.username)
            cityET.setText(receivedUser.city)
            locationET.setText(location)
            phoneET.setText(receivedUser.phone_number)
        }







        // Setup Save button click listener
        saveBtn.setOnClickListener {
            savedata(receivedUser,location);
            finish() // Finish activity after saving
        }

        // Setup Cancel button click listener
        cancelBtn.setOnClickListener {
            finish() // Finish activity without saving changes
        }
    }
    fun savedata(receivedUser:User?,location:String?){
        if (receivedUser != null) {
            nameET.setText(receivedUser.username)
            cityET.setText(receivedUser.city)
            locationET.setText(location)
            phoneET.setText(receivedUser.phone_number)



            val call = RetrofitClient.instance.updateUser(
                receivedUser.id.toString(),
                receivedUser.username,
                receivedUser.phone_number,
                receivedUser.longitude,
                receivedUser.latitude,
                receivedUser.city,
                "0")
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Log.d("editProfileTAG","Profile updated successfully")
                        // Handle success, if needed
                    } else {

                        Log.d("editProfileTAG","Failed to update profile")
                        // Handle failure, if needed
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("editProfileTAG","Error: ${t.message}")
                    // Handle failure, if needed
                }
            })
        }


    }
    }
