package com.example.lostpeoplefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lostpeoplefinder.API.RetrofitClient
import com.example.yourapp.RememberHandler
import okhttp3.ResponseBody
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
    lateinit var o:RememberHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        // Retrieve data passed via Intent extras
        //val name = intent.getStringExtra("name")
        //val city = intent.getStringExtra("city")
        //val mobile = intent.getStringExtra("mob")

//         val location = intent.getStringExtra("loc")
//         val receivedUser = intent.getSerializableExtra("user") as? User
//
//        Log.d("editProfileTAG",location.toString())
//        Log.d("editProfileTAG",receivedUser?.username.toString())


         o=RememberHandler.getInstance(this@EditProfileActivity)

        // Set data into EditText fields
        nameET=findViewById(R.id.editTextName)
        cityET=findViewById(R.id.editTextCity)
        locationET=findViewById(R.id.editTextLocation)
        phoneET=findViewById(R.id.editTextMobile)
        saveBtn=findViewById(R.id.buttonSave)
        cancelBtn=findViewById(R.id.buttonCancel)



            nameET.setText(o.getUserName().toString())
            cityET.setText(o.getUserCity().toString())
            locationET.setText(o.getUserLocation().toString())
            phoneET.setText(o.getUserPhone().toString())







        // Setup Save button click listener
        saveBtn.setOnClickListener {
            savedata();
            //finish();
        }

        // Setup Cancel button click listener
        cancelBtn.setOnClickListener {
            finish() // Finish activity without saving changes
        }
    }
    fun savedata(){
        val msg=validSaving()
        if(msg.length>0){
            Toast.makeText(this@EditProfileActivity, msg, Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("qqqq",nameET.text.toString())
        Log.d("qqqq",phoneET.text.toString())
        Log.d("qqqq",cityET.text.toString())

        o.setUserName(nameET.text.toString())
        o.setUserCity(cityET.text.toString())
        o.setUserLocation(locationET.text.toString())
        o.setUserPhone(phoneET.text.toString());

        val call = RetrofitClient.instance.updateUser(
            id=o.getUserId().toString(),
            username = o.getUserName().toString(),
            phoneNumber = o.getUserPhone().toString(),
            lng=o.getUser_Long_itutde()!!.toDouble(),
            lat=o.getUser_Lat_itude()!!.toDouble(),
            city = o.getUserCity().toString(),
            notifications = "0"
        )
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EditProfileActivity, "User profile updated successfully", Toast.LENGTH_SHORT).show()
                    finish()

                } else {
                    Toast.makeText(this@EditProfileActivity, "Failed to update profile", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@EditProfileActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })





        }

 fun validSaving():String{
     if(nameET.text.isNullOrEmpty())return "name is required"
     if(cityET.text.isNullOrEmpty())return "city is required"
     if(phoneET.text.isNullOrEmpty())return "mobile is required"
     if(locationET.text.isNullOrEmpty())return "Location is required"
     return ""

 }


}

