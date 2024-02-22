package com.example.lostpeoplefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lostpeoplefinder.API.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val btnSignUp: Button = findViewById(R.id.btnSignUp)
        val editTextUsername:EditText=findViewById(R.id.editTextUsername)
        val editTextEmail:EditText=findViewById(R.id.editTextEmail)
        val editTextPassword:EditText=findViewById(R.id.editTextPassword)
        val editTextPhoneNumber:EditText=findViewById(R.id.editTextPhoneNumber)
        btnSignUp.setOnClickListener {
            val username = editTextUsername.text.toString()
            val email = editTextEmail.text.toString()
            val password =editTextPassword.text.toString()
            val phoneNumber = editTextPhoneNumber.text.toString()

            val userData = UserData(username, email, password, phoneNumber)

            // Make sign up request
            val call = RetrofitClient.instance.registerUser(userData)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        Toast.makeText(this@SignUp, message, Toast.LENGTH_SHORT).show()
                    } else {
                        val error = response.body()?.error
                        Toast.makeText(this@SignUp, error, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Toast.makeText(this@SignUp, "Failed to sign up", Toast.LENGTH_SHORT).show()
                }
            })
        }


    }
}