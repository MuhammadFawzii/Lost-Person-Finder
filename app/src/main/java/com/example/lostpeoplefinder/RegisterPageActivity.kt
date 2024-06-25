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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterPageActivity : AppCompatActivity() {
   private var token=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regisiter_page)
       // FirebaseApp.initializeApp(this)
        // Check if the default Firebase app is initialized

        var token=""
        //Toast.makeText(this, "Firebase is not initialized", Toast.LENGTH_SHORT).show()
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("bkr", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            token = task.result
            if (token != null) {
                // Log and toast
                Log.d("bkr", token)
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()

            } else {
                Log.w("bkr", "FCM registration token is null")
            }
        }



        val btnSignUp: Button = findViewById(R.id.bt_Next_Register)
        var textViewLogin: TextView =findViewById(R.id.go_login)

        val editTextUsername:EditText=findViewById(R.id.et_full_name_register)
        val editTextEmail:EditText=findViewById(R.id.et_email_register)
        val editTextPassword:EditText=findViewById(R.id.et_password_reg)
        val editTextPhoneNumber:EditText=findViewById(R.id.et_phone_register)
        btnSignUp.setOnClickListener {
            val username = editTextUsername.text.toString()
            val email = editTextEmail.text.toString()
            val password =editTextPassword.text.toString()
            val phoneNumber = editTextPhoneNumber.text.toString()

            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            val passwordPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}"
            val phoneNumberPattern = "\\d{11}"
            val isEmailValid = email.matches(emailPattern.toRegex())
            val isPasswordValid = password.matches(passwordPattern.toRegex())
            val isPhoneNumberValid = phoneNumber.matches(phoneNumberPattern.toRegex())

            if (!isEmailValid) {
                Toast.makeText(this, "Email not valid \n Should be like example@gmail.com", Toast.LENGTH_LONG).show()
            }

            if (!isPasswordValid) {
                Toast.makeText(this, "Password not valid \n Should contain capital and small letters", Toast.LENGTH_LONG).show()
            }

            if (!isPhoneNumberValid) {
                Toast.makeText(this, "Phone Number not valid \n Should be 11 char", Toast.LENGTH_LONG).show()
            }

            if (isEmailValid && isPasswordValid && isPhoneNumberValid) {
                val userData = UserData(username, email, password, phoneNumber,token)
                // Make sign up request
                val call = RetrofitClient.instance.registerUser(userData)
                call.enqueue(object : Callback<ApiResponse> {
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        if (response.isSuccessful) {
                            val message = response.body()?.message
                            Log.d("00++++", message.toString())
                            if (message.equals("A verification code has been sent to your email.")) {
                                val intent = Intent(this@RegisterPageActivity, VerifyCodeActivity::class.java)
                                val bundle = Bundle()
                                bundle.putSerializable("userdata", userData)
                                intent.putExtras(bundle)
                                startActivity(intent)
                            }
                            Toast.makeText(this@RegisterPageActivity, message, Toast.LENGTH_SHORT).show()
                        } else {
                            val error = response.body()?.error
                            Log.d("+++0", error.toString())
                            Toast.makeText(
                                this@RegisterPageActivity,
                                "Something wrong please try again.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Log.d("+++", t.message.toString())
                        Toast.makeText(this@RegisterPageActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
        textViewLogin.setOnClickListener {
            var intent=Intent(this,LoginPageActivity::class.java)
            startActivity(intent)
        }

    }
}