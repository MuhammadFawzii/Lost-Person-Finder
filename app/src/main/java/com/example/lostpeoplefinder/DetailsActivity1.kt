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
            Toast.makeText(this, selectedGender.toString(), Toast.LENGTH_SHORT).show()
            val date=DOBirth.text.toString()
            if(name.isNotEmpty() &&(selectedGender.equals("Male")||selectedGender.equals("Female"))&&date.isNotEmpty()){
                val intent=Intent(this, DetailsActivity3::class.java)
                startActivity(intent)
            }

            val age=age.text.toString()
            if(name.isNotEmpty()&&(selectedGender.equals("Male")||selectedGender.equals("Female"))&&age.isNotEmpty()){
                val intent=Intent(this, DetailsActivity3::class.java)
                intent.putExtra("name",name)
                intent.putExtra("gender",selectedGender.toString())
                intent.putExtra("age",age)
                intent.putExtra("report", report_name)
                startActivity(intent)
            }
        }
        btn_previous1.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            R.style.MyDatePickerDialogTheme,
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
