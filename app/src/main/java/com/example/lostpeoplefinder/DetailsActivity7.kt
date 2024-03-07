
package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class DetailsActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details7)
        val nextButton=findViewById<Button>(R.id.bt_next7)
        val checkbox = findViewById<CheckBox>(R.id.checkbox_privacy_terms)
        val termsLink = findViewById<TextView>(R.id.terms_link)
        val policyLink = findViewById<TextView>(R.id.policy_link)

        termsLink.setOnClickListener {
            val intent = Intent(this, TermsPolicyActivity::class.java) // Use class reference
            startActivity(intent)
        }
        policyLink.setOnClickListener {
            val intent = Intent(this, TermsPolicyActivity::class.java) // Use class reference
            startActivity(intent)
        }

        checkbox.setOnClickListener {
            if (checkbox.isChecked) {
                // Handle the checkbox being checked
                nextButton.isEnabled = true
            } else {
                // Handle the checkbox being unchecked
                nextButton.isEnabled = false
            }
        }

        nextButton.setOnClickListener {
            // Handle the next button being clicked
            // For example, navigate to the next screen
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }


    }
}






