package com.example.lostpeoplefinder

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class DetailsActivity1 : AppCompatActivity() {
    lateinit var ETName:TextInputEditText
    lateinit var age:EditText
    lateinit var gender:Spinner
    lateinit var report:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details1)
        val report_name = intent.getStringExtra("report")
        ETName = findViewById(R.id.name)
        age = findViewById(R.id.age)
        val btn_next1 = findViewById<Button>(R.id.btn_next1)
        val btn_previous1 = findViewById<Button>(R.id.btn_previous1)
        gender = findViewById(R.id.textInputLayout2)
        report=findViewById(R.id.report)
        getData()

        val genders = arrayOf("Male", "Female")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genders)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gender.adapter = adapter

        btn_next1.setOnClickListener {
            val name=ETName.text.toString()
            val selectedGender=gender.selectedItem
            //Toast.makeText(this, selectedGender.toString(), Toast.LENGTH_SHORT).show()
            val age=age.text.toString()
            val x=if (selectedGender == "Male")
                1
             else
                2

            if(name.isNotEmpty()&&(selectedGender.equals("Male")||selectedGender.equals("Female"))&&age.isNotEmpty()){
                if(Integer.parseInt(age)>100){
                    Toast.makeText(this, "age must be less than 100 year", Toast.LENGTH_SHORT).show()
                }else{


                    ReportDataBuffer.updateCheckLost(report_name)
                    ReportDataBuffer.updatePersonName(name)
                    ReportDataBuffer.updateAge(age)
                    ReportDataBuffer.updateGender(x)
                    Log.d("d1",ReportDataBuffer.toString())



//                    val intent=Intent(this, DetailsActivity3::class.java)
//                intent.putExtra("name",name)
//                intent.putExtra("gender",x)
//                intent.putExtra("age",age)
//                intent.putExtra("report", report_name)
                startActivity(Intent(this, DetailsActivity3::class.java))
              }
            }
            else {
                Toast.makeText(this, "Fill all data.", Toast.LENGTH_SHORT).show()
            }
        }
        btn_previous1.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
    fun getData(){
        val name:String=intent.getStringExtra("name")?:""
        if(name!=null)
            ETName.setText(name)
        val ageText:String=intent.getStringExtra("age")?:""
        if(ageText!=null)
            age.setText(ageText)
        val genderText:String=intent.getStringExtra("gender")?:"0"
        Toast.makeText(this, genderText, Toast.LENGTH_SHORT).show()
        if(genderText!=null)
            gender.setSelection(Integer.parseInt(genderText))

        val reportNum:String=intent.getStringExtra("report")?:""
        if(reportNum!=null)
        if(reportNum.equals("1"))
            report.text="Found Person Details"
        Toast.makeText(this@DetailsActivity1, reportNum, Toast.LENGTH_SHORT)
            .show()
    }
}
