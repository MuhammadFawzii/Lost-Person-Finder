package com.example.lostpeoplefinder

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ContactUsActivity : AppCompatActivity() {
    lateinit var firstNameEditText:TextInputEditText
    lateinit var lastNameEditText:TextInputEditText
    lateinit var emailEditText:TextInputEditText
    lateinit var phoneEditText:TextInputEditText
    lateinit var emailBox:TextInputLayout
    lateinit var phoneBox: TextInputLayout
    lateinit var checkbox:CheckBox
    lateinit var privacy_txt:TextView
    lateinit var nextBtn:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        firstNameEditText=findViewById(R.id.contactUs_firstNameEditText)
        lastNameEditText=findViewById(R.id.contactUs_lastName_EditText)
        emailEditText=findViewById(R.id.contactUs_emailEditText)
        phoneEditText=findViewById(R.id.contactUs_phoneNummberEditText)
        emailBox=findViewById(R.id.contactUs_email_Layout)
        phoneBox=findViewById(R.id.contactUS_phone_Layout)
        checkbox=findViewById(R.id.contactUs_checkboxPrivacyTerms)
        nextBtn=findViewById(R.id.contactUs_NextBtn)
        privacy_txt=findViewById(R.id.contactUsPolicyLink)

        privacy_txt.setOnClickListener {
            val intent = Intent(this, PrivacePolicyActivity::class.java) // Use class reference
            startActivity(intent)
        }

        checkbox.setOnClickListener {
            nextBtn.isEnabled=checkbox.isChecked
        }

        nextBtn.setOnClickListener {
            val intent = Intent(this, DetailsCompleteActivity8::class.java)
            startActivity(intent)
        }


        emailEditText.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus && emailEditText.getText().toString().isEmpty()) {
                    emailEditText.setHint("you@company.com")
                } else {
                    emailEditText.setHint("")
                }
            }
        })
        phoneEditText.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus && phoneEditText.getText().toString().isEmpty()) {
                    phoneBox.prefixText="+20"
                } else {
                   phoneBox.prefixText="";
                }
            }
        })

    }
}