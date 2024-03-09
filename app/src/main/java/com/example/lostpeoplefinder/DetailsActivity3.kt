package com.example.lostpeoplefinder

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.util.*

class DetailsActivity3 : AppCompatActivity() {
    lateinit var date:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details4)
        val name=intent.getStringExtra("name")
        val age=intent.getStringExtra("age")
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        val gender=intent.getStringExtra("gender")
        val btn_next4=findViewById<Button>(R.id.bt_next4)
        val btn_previous4=findViewById<Button>(R.id.btn_previous4)
        val calendarIcon = findViewById<ImageView>(R.id.calender)
         date=findViewById<EditText>(R.id.et_last_Data)
        btn_next4.setOnClickListener {
            val date=date.text.toString()
            val lang="160"
            val lat="120"
            if(date.isNotEmpty()) {
                val intent = Intent(this, DetailsActivity4::class.java)
                intent.putExtra("name", name)
                intent.putExtra("gender", gender)
                intent.putExtra("age", age)
                intent.putExtra("date",date)
                intent.putExtra("lang", lang)
                intent.putExtra("lat",lat)
                startActivity(intent)
            }
        }
        btn_previous4.setOnClickListener {
            startActivity(Intent(this,DetailsActivity1::class.java))
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
                date.setText(selectedDate)
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }
}