package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        var textViewLogin: TextView =findViewById(R.id.textViewLogin)

        val editTextUsername:EditText=findViewById(R.id.editTextUsername)
        val editTextEmail:EditText=findViewById(R.id.editTextEmail)
        val editTextPassword:EditText=findViewById(R.id.editTextPassword)
        val editTextPhoneNumber:EditText=findViewById(R.id.editTextPhoneNumber)
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
              val userData = UserData(username, email, password, phoneNumber)
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
                              val intent = Intent(this@SignUp, VerifyCodeActivity::class.java)
                              val bundle = Bundle()
                              bundle.putSerializable("userdata", userData)
                              intent.putExtras(bundle)
                              startActivity(intent)
                          }
                          Toast.makeText(this@SignUp, message, Toast.LENGTH_SHORT).show()
                      } else {
                          val error = response.body()?.error
                          Log.d("+++0", error.toString())
                          Toast.makeText(
                              this@SignUp,
                              "Something wrong please try again.",
                              Toast.LENGTH_SHORT
                          ).show()
                      }
                  }

                  override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                      Log.d("+++", t.message.toString())
                      Toast.makeText(this@SignUp, t.message, Toast.LENGTH_SHORT).show()
                  }
              })
          }
        }
        textViewLogin.setOnClickListener {
            var intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

    }
}