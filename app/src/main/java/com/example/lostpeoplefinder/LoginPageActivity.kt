package com.example.lostpeoplefinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lostpeoplefinder.API.RetrofitClient
import com.example.yourapp.RememberHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.location.Geocoder
import android.location.Address
import java.io.IOException
import java.util.Locale


class LoginPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        var textViewSignUp: TextView = findViewById(R.id.tv_register_login)
        var btnLogin: Button = findViewById(R.id.btn_loginPage)
        var editTextEmail: EditText = findViewById(R.id.et_email_login)
        var editTextPassword: EditText = findViewById(R.id.et_password_login)
        var forgetPassword:TextView=findViewById(R.id.tv_forgetPassword_Login)

//        val email = "ahmedhomes611@gmail.com"
//        val password = "Bakr1234"
//        editTextEmail.setText(email);
//        editTextPassword.setText(password);

        btnLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()


            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            val isEmailValid = email.matches(emailPattern.toRegex())

            if (!isEmailValid) {
                Toast.makeText(
                    this,
                    "Email not valid \n Should be like example@gmail.com",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                val loginRequest = LoginData(email, password)
                Log.d("++ms", email+" "+password);

                // Make login request
                val call = RetrofitClient.instance.loginUser(loginRequest)
                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            val message = loginResponse?.message
                            Log.d("++++", message.toString())
                            Log.d("++ms", email+" "+password);


                            if (message == "Login successful") {
                                val userId = loginResponse.id
                                userId?.let {
                                    saveCurrentUser(it)
                                    startActivity(Intent(this@LoginPageActivity, HomeActivity::class.java))
                                }
                            }
                            Toast.makeText(this@LoginPageActivity, message, Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d("00++", "Login request failed, response code: ${response.code()}")
                            Toast.makeText(this@LoginPageActivity, "Invalid email or password", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginPageActivity, "Failed to login", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }


        textViewSignUp.setOnClickListener {
            var intent = Intent(this, RegisterPageActivity::class.java)
            startActivity(intent)
        }
        forgetPassword.setOnClickListener {
            var intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setRememberMeWhenLoginSuccess(email:String,password:String){
        RememberMeHandler.getInstance(this).createRememberMeSession(email,password,false);
    }
    fun saveCurrentUser(id:Int){
        val call = RetrofitClient.instance.getUser(id) // Use a default value if user ID is null

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {

                        val o=RememberHandler.getInstance(this@LoginPageActivity)
                            o.setUserId(user.id)
                            o.setUserCity(user.city)
                            o.setUserEmail(user.email)
                            o.setUserName(user.username)
                            o.setUserPhone(user.phone_number)
                            o.setUser_Long_itutde(user.longitude.toString())
                            o.setUser_Lat_itude(user.latitude.toString())
                            o.setUserNotification(user.notification_enabled)
                            o.setUserToken(user.token)
                        o.setUserLocation(getAddressFromLocation(this@LoginPageActivity,user.longitude,user.latitude).toString())
                        Log.d("hhg", o.toString())
                        Log.d("loginTAG", "User saved successfully")

                    } else {
                        Log.d("loginTAG", "User object is null")
                    }
                } else {
                    Log.d("loginTAG", "Response failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("loginTAG", "Failed to fetch user details: ${t.message}")
            }
        })

    }
    fun getAddressFromLocation(context: Context, latitude: Double, longitude: Double): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        return try {
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 4)
            if (addresses != null && addresses.isNotEmpty()) {
                val address: Address = addresses[0]
                val addressLines = StringBuilder()
                for (i in 0..address.maxAddressLineIndex) {
                    addressLines.append(address.getAddressLine(i)).append("\n")
                }
                Log.d("addddd",addressLines.toString().trim())
                addressLines.toString().trim() // Trim to remove the last newline
            } else {
                Log.d("loginLoc","address is null");
                null
            }
        } catch (e: IOException) {
            Log.d("loginLoc",e.message.toString());
            e.printStackTrace()
            null
        }
    }


}




/*
btnLogin.setOnClickListener {
           val email = editTextEmail.text.toString()
           val password = editTextPassword.text.toString()
           val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
           val isEmailValid = email.matches(emailPattern.toRegex())
           if (!isEmailValid) {
               Toast.makeText(
                   this,
                   "Email not valid \n Should be like example@gmail.com",
                   Toast.LENGTH_LONG
               ).show()
           } else {
               val loginData = LoginData(email, password)

                // Make login request
                val call = RetrofitClient.instance.loginUser(loginData)
                call.enqueue(object : Callback<ApiResponse> {
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            Log.d("++++", message.toString())
                            if (message.equals("Login successful")) {
                                startActivity(Intent(this@LoginPageActivity, HomeActivity::class.java))
                            }
                            Toast.makeText(this@LoginPageActivity, message, Toast.LENGTH_SHORT).show()
                        } else {
                            val error = response.body()?.error
                            Log.d("00++", error.toString())
                            Toast.makeText(this@LoginPageActivity, error, Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Toast.makeText(this@LoginPageActivity, "Failed to login", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }

        }
        textViewSignUp.setOnClickListener {
            var intent = Intent(this, RegisterPageActivity::class.java)
            startActivity(intent)
        }
        forgetPassword.setOnClickListener {
            var intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setRememberMeWhenLoginSuccess(email:String,password:String){
        RememberMeHandler.getInstance(this).createRememberMeSession(email,password,false);
    }
}*/