package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.text.TextUtils // For checking empty strings
import android.text.Editable // Represents editable text
import android.text.TextWatcher
import android.widget.EditText


class RegisiterPageActivity : AppCompatActivity() {
    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var checkBox: CheckBox
    private lateinit var btnNextRegister: Button
    private lateinit var termsLink:TextView
    private lateinit var policyLink:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regisiter_page)

        // Initialize views
        initViews()
        // Add a TextWatcher to each EditText to dynamically update the button's state
        addTextWatchersToEditTexts()
        // Add a listener for the checkbox
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            updateButtonState()
        }
        setListeners()

    }

    private fun initViews() {
        etFullName = findViewById(R.id.et_full_name_register)
        etEmail = findViewById(R.id.et_email_register)
        etPhone = findViewById(R.id.et_phone_register)
        etPassword = findViewById(R.id.et_password_reg)
        checkBox = findViewById(R.id.checkbox_privacy_terms_reg)
        btnNextRegister = findViewById(R.id.bt_Next_Register)
        termsLink = findViewById(R.id.terms_link_reg)
        policyLink = findViewById(R.id.policy_link_reg)
    }
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            updateButtonState()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
    private fun addTextWatchersToEditTexts() {
        etFullName.addTextChangedListener(textWatcher)
        etEmail.addTextChangedListener(textWatcher)
        etPhone.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
    }

    private fun updateButtonState() {
        val isChecked = checkBox.isChecked
        val isFullNameValid = !TextUtils.isEmpty(etFullName.text.toString())
        val isEmailValid = !TextUtils.isEmpty(etEmail.text.toString())
        val isPhoneValid = !TextUtils.isEmpty(etPhone.text.toString())
        val isPasswordValid = !TextUtils.isEmpty(etPassword.text.toString())
        btnNextRegister.isEnabled = isChecked && isFullNameValid && isEmailValid && isPhoneValid && isPasswordValid
    }
    private fun setListeners() {
        termsLink.setOnClickListener {
            val intent = Intent(this, PrivacePolicyActivity::class.java) // Use class reference
            startActivity(intent)
        }
        policyLink.setOnClickListener {
            val intent = Intent(this, PrivacePolicyActivity::class.java) // Use class reference
            startActivity(intent)
        }
        btnNextRegister.setOnClickListener {
            val intent = Intent(this, VerifyCodeActivity::class.java)
            startActivity(intent)
        }
    }


}