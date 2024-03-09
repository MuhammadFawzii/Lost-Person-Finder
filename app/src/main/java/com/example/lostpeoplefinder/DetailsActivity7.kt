
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
        val checkbox = findViewById<CheckBox>(R.id.checkbox_privacy_terms)
        val termsLink = findViewById<TextView>(R.id.terms_link)
        val policyLink = findViewById<TextView>(R.id.policy_link)
        val btn_next=findViewById<Button>(R.id.bt_next7)
        val btn_previous=findViewById<Button>(R.id.bt_previous7)

        termsLink.setOnClickListener {
            val intent = Intent(this, TermsPolicyActivity::class.java) // Use class reference
            startActivity(intent)
        }
        policyLink.setOnClickListener {
            val intent = Intent(this, TermsPolicyActivity::class.java) // Use class reference
            startActivity(intent)
        }

        checkbox.setOnClickListener {
            btn_next.isEnabled = checkbox.isChecked
        }

        btn_next.setOnClickListener {
            startActivity(Intent(this,DetailsCompleteActivity8::class.java))
        }
        btn_previous.setOnClickListener {
            startActivity(Intent(this,DetailsActivity6::class.java))
        }

    }
}






