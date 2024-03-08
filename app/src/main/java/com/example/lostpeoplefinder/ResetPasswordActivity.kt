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

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email)
        val email=intent.getStringExtra("email").toString()
        val etNewPassword:EditText=findViewById(R.id.et_newPassword)
        val etConfirm:EditText=findViewById(R.id.et_confirmPassword)
        val reseat:Button=findViewById(R.id.reseat)
        val passwordPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}"

        reseat.setOnClickListener {
            val newPass=etNewPassword.text.toString()
            val conPass=etConfirm.text.toString()

            if (newPass != conPass){
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
            }
            else{
                val isPasswordValid = newPass.matches(passwordPattern.toRegex())
                if (!isPasswordValid) {
                    Toast.makeText(this, "Password not valid \n Should contain capital and small letters and numbers", Toast.LENGTH_LONG).show()
                }else {
                    val call = RetrofitClient.instance.setNewPassword(email, newPass)
                    call.enqueue(object : Callback<ApiResponse> {
                        override fun onResponse(
                            call: Call<ApiResponse>,
                            response: Response<ApiResponse>
                        ) {
                            if (response.isSuccessful) {
                                val message = response.body()?.message
                                Log.d("00", message.toString())
                                val intent =
                                    Intent(this@ResetPasswordActivity, LoginActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(
                                    this@ResetPasswordActivity,
                                    message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val error = response.body()?.error
                                Log.d("+++0", error.toString())
                                Toast.makeText(
                                    this@ResetPasswordActivity,
                                    error,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                            Log.d("+++", t.message.toString())
                            Toast.makeText(
                                this@ResetPasswordActivity,
                                t.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }
            }
        }
    }
}