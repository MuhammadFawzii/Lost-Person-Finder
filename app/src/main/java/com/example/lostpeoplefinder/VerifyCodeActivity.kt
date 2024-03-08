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

class VerifyCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_code)
        val bundle = intent.extras
        val userData = bundle?.getSerializable("userdata") as? UserData
        val code=findViewById<EditText>(R.id.code)

        //Toast.makeText(this, userData!!.username.toString(), Toast.LENGTH_SHORT).show()
        val submit=findViewById<Button>(R.id.submit)
        submit.setOnClickListener {
            var c=code.text.toString()
            val call=RetrofitClient.instance.verifyCode(userData!!.username,userData.password,userData.phone_number,userData.email,c)
            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val message = response.body()?.message
                        if(message.equals("User registered successfully")) {
                            Log.d("00", message.toString())
                            startActivity(
                                Intent(
                                    this@VerifyCodeActivity,
                                    LoginActivity::class.java
                                )
                            )
                        }
                        Toast.makeText(this@VerifyCodeActivity, message, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val error = response.body()?.error
                        Log.d("+++0",error.toString())
                        Toast.makeText(this@VerifyCodeActivity, error, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.d("+++",t.message.toString())
                    Toast.makeText(this@VerifyCodeActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }


    }
}