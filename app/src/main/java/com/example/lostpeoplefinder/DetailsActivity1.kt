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
        val report_name = intent.getStringExtra("report")
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
        gender.adapter = adapter
        Toast.makeText(this@DetailsActivity1, report_name.toString(), Toast.LENGTH_SHORT)
            .show()
        btn_next1.setOnClickListener {
            val name=ETName.text.toString()
            val selectedGender=gender.selectedItem
            //Toast.makeText(this, selectedGender.toString(), Toast.LENGTH_SHORT).show()
            val age=age.text.toString()
            val x=if (selectedGender == "Male") {
                "1"
            } else {
                "2"
            }
            if(name.isNotEmpty()&&(selectedGender.equals("Male")||selectedGender.equals("Female"))&&age.isNotEmpty()&&age.length<=2){
                val intent=Intent(this, DetailsActivity3::class.java)
                intent.putExtra("name",name)
                intent.putExtra("gender",x)
                intent.putExtra("age",age)
                intent.putExtra("report", report_name)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Fill all data.", Toast.LENGTH_SHORT).show()

            }
        }
        btn_previous1.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
