package com.example.lostpeoplefinder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lostpeoplefinder.API.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        var textViewSignUp: TextView = findViewById(R.id.tv_register_login)
        var btnLogin: Button = findViewById(R.id.btn_loginPage)
        var editTextEmail: EditText = findViewById(R.id.et_email_login)
        var editTextPassword: EditText = findViewById(R.id.et_password_login)
        var forgetPassword:TextView=findViewById(R.id.tv_forgetPassword_Login)
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
}