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

class VerifyEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_code)
        /*val email = intent.getStringExtra("email").toString()
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show()
        val code = findViewById<EditText>(R.id.code)
        val submit = findViewById<Button>(R.id.submit)
        submit.setOnClickListener {
            var c = code.text.toString()
            val call = RetrofitClient.instance.verifyResetPassword(email, c)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        Log.d("00", message.toString())
                        if (message.equals("Correct code.")) {
                            val intent =
                                Intent(this@VerifyEmailActivity, ResetPasswordActivity::class.java)
                            intent.putExtra("email", email)
                            startActivity(intent)
                        }
                        Toast.makeText(this@VerifyEmailActivity, message, Toast.LENGTH_SHORT).show()
                    } else {
                        val error = response.body()?.error
                        Log.d("+++0", error.toString())
                        Toast.makeText(this@VerifyEmailActivity, error, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.d("+++", t.message.toString())
                    Toast.makeText(this@VerifyEmailActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }*/
    }
}