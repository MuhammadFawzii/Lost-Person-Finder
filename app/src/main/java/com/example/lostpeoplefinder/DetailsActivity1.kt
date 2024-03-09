package com.example.lostpeoplefinder

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

import com.google.android.gms.cast.framework.media.ImagePicker
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class DetailsActivity1 : AppCompatActivity() {
    lateinit var DOBirth:TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details1)
        val ETName = findViewById<TextInputEditText>(R.id.name)
        DOBirth = findViewById(R.id.DOBirth)
        val calendarIcon = findViewById<ImageView>(R.id.calender_btn)
        val btn_next1 = findViewById<Button>(R.id.btn_next1)
        val btn_previous1 = findViewById<Button>(R.id.btn_previous1)
        val gender: Spinner = findViewById(R.id.textInputLayout2)

        // Define the options
        val genders = arrayOf("Male", "Female")

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genders)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        gender.adapter = adapter

        btn_next1.setOnClickListener {
            val name=ETName.text.toString()
            val gender=gender.selectedItem
            Toast.makeText(this, gender.toString(), Toast.LENGTH_SHORT).show()
            val date=DOBirth.text.toString()
            //if(name.isEmpty()&&(gender.equals("Male")||gender.equals("Female")))
            startActivity(Intent(this, DetailsActivity3::class.java))
        }
        btn_previous1.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        calendarIcon.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${selectedMonth + 1}/$selectedYear"
                DOBirth.setText(selectedDate)
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }
}
