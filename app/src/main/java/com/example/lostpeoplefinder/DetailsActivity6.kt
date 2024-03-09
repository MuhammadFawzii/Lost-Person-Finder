
package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.example.lostpeoplefinder.API.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details7)
        val name=intent.getStringExtra("name")
        val age=intent.getStringExtra("age")
        val gender=intent.getStringExtra("gender")
        val date=intent.getStringExtra("date")
        val number=intent.getStringExtra("number")
        val email=intent.getStringExtra("email")
        val note=intent.getStringExtra("note")
        val lang=intent.getStringExtra("lang")
        val lat=intent.getStringExtra("lat")

        val checkbox = findViewById<CheckBox>(R.id.checkbox_privacy_terms)
        val termsLink = findViewById<TextView>(R.id.terms_link)
        val policyLink = findViewById<TextView>(R.id.policy_link)
        val btn_next=findViewById<Button>(R.id.bt_next7)
        val btn_previous=findViewById<Button>(R.id.bt_previous7)

        termsLink.setOnClickListener {
            val intent = Intent(this, PrivacePolicyActivity::class.java) // Use class reference
            startActivity(intent)
        }
        policyLink.setOnClickListener {
            val intent = Intent(this, PrivacePolicyActivity::class.java) // Use class reference
            startActivity(intent)
        }

        checkbox.setOnClickListener {
            btn_next.isEnabled = checkbox.isChecked
        }
        val lostPerson =null
        btn_next.setOnClickListener {
            val intent=Intent(this,DetailsActivity7::class.java)
            intent.putExtra("name", name)
            intent.putExtra("gender", gender)
            intent.putExtra("age", age)
            intent.putExtra("date",date)
            intent.putExtra("number",number)
            intent.putExtra("email",email)
            intent.putExtra("note",note)
            intent.putExtra("lang", lang)
            intent.putExtra("lat",lat)
            startActivity(intent)
        }
        btn_previous.setOnClickListener {
            startActivity(Intent(this,DetailsActivity5::class.java))
        }

    }
}






