package com.example.lostpeoplefinder

import android.content.Intent
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


class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        val emailEditText:EditText=findViewById(R.id.emailEditText)
        val sendResetCodeButton:Button=findViewById(R.id.sendResetCodeButton)
        sendResetCodeButton.setOnClickListener {
            val email=emailEditText.text.toString()
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            val isEmailValid = email.matches(emailPattern.toRegex())
            if (!isEmailValid) {
                Toast.makeText(
                    this,
                    "Email not valid \n Should be like example@gmail.com",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val call = RetrofitClient.instance.forgotPassword(email)
                call.enqueue(object : Callback<ApiResponse> {
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            Log.d("00++++", message.toString())
                            if (message.equals("A verification code has been sent to your email.")) {
                                val intent = Intent(this@ForgetPasswordActivity, VerifyEmailActivity::class.java)
                                intent.putExtra("email",email)
                                startActivity(intent)
                            }
                            Toast.makeText(this@ForgetPasswordActivity, message, Toast.LENGTH_SHORT).show()
                        } else {
                            val error = response.body()?.error
                            Log.d("+++0", error.toString())
                            Toast.makeText(
                                this@ForgetPasswordActivity,
                                "Something wrong please try again.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Log.d("onFailure", t.message.toString())
                        Toast.makeText(this@ForgetPasswordActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })



            }
        }
    }
}