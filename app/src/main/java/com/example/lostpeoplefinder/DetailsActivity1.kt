package com.example.lostpeoplefinder

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast

import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class DetailsActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details1)
        val ETName = findViewById<TextInputEditText>(R.id.name)
        val age = findViewById<EditText>(R.id.age)
        val btn_next1 = findViewById<Button>(R.id.btn_next1)
        val btn_previous1 = findViewById<Button>(R.id.btn_previous1)
        val gender: Spinner = findViewById(R.id.textInputLayout2)
        val genders = arrayOf("Male", "Female")

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genders)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        gender.adapter = adapter

        btn_next1.setOnClickListener {
            val name=ETName.text.toString()
            val selectedGender=gender.selectedItem
            Toast.makeText(this, selectedGender.toString(), Toast.LENGTH_SHORT).show()
            val age=age.text.toString()
            if(name.isNotEmpty()&&(selectedGender.equals("Male")||selectedGender.equals("Female"))&&age.isNotEmpty()){
                val intent=Intent(this, DetailsActivity3::class.java)
                intent.putExtra("name",name)
                intent.putExtra("gender",selectedGender.toString())
                intent.putExtra("age",age)
                startActivity(intent)
            }
        }
        btn_previous1.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

}
