package com.example.lostpeoplefinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lostpeoplefinder.API.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textViewSignUp: TextView =findViewById(R.id.textViewSignUp)
       var btnLogin: Button =findViewById(R.id.btnLogin)
       var editTextUsername: EditText =findViewById(R.id.editTextUsername)
       var editTextPassword: EditText =findViewById(R.id.editTextPassword)
        btnLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            val loginData = LoginData(username, password)

            // Make login request
            val call = RetrofitClient.instance.loginUser(loginData)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                        // Navigate to next activity or perform necessary actions upon successful login
                    } else {
                        val error = response.body()?.error
                        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed to login", Toast.LENGTH_SHORT).show()
                }
            })
        }
        textViewSignUp.setOnClickListener {
             var intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }
    }

}