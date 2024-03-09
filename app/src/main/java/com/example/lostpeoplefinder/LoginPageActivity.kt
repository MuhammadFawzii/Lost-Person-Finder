package com.example.lostpeoplefinder

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.text.TextUtils // For checking empty strings
import android.text.Editable // Represents editable text
import android.text.TextWatcher
import android.widget.EditText
class LoginPageActivity : AppCompatActivity() {
    private lateinit var et_Email: EditText
    private lateinit var et_Password: EditText
    private lateinit var tv_forgetPassword: TextView
    private lateinit var tv_register: TextView
    private lateinit var btn_Login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        et_Email = findViewById(R.id.et_email_login)
        et_Password = findViewById(R.id.et_password_login)
        tv_forgetPassword = findViewById(R.id.tv_forgetPassword_Login)
        tv_register = findViewById(R.id.tv_register_login)
        btn_Login=findViewById(R.id.btn_loginPage)

        // Add a TextWatcher to each EditText to dynamically update the button's state
        addTextWatchersToEditTexts()
        // Add a listener for the checkbox
        updateButtonState()

        setListeners()

    }
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            updateButtonState()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    private fun addTextWatchersToEditTexts() {
        et_Email.addTextChangedListener(textWatcher)
        et_Password.addTextChangedListener(textWatcher)
    }
    private fun updateButtonState() {
        val isEmailValid = !TextUtils.isEmpty(et_Email.text.toString())
        val isPasswordValid = !TextUtils.isEmpty(et_Password.text.toString())
        btn_Login.isEnabled =  isEmailValid && isPasswordValid
    }
    private fun setListeners() {
        tv_forgetPassword.setOnClickListener {
            // Handle click on "Forget Password" text
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        tv_register.setOnClickListener {
            // Handle click on "Register" text
            val intent = Intent(this, RegisiterPageActivity::class.java)
            startActivity(intent)
        }
        btn_Login.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setRememberMeWhenLoginSuccess(email:String,password:String){
        RememberMeHandler.getInstance(this).createRememberMeSession(email,password,false);
    }
}