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
import android.widget.TextView
import android.widget.Toast

import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class DetailsActivity1 : AppCompatActivity() {
    private val REQUEST_CODE = 1
    lateinit var ETName:TextInputEditText
    lateinit var age:EditText
    lateinit var gender:Spinner
    lateinit var report:TextView
    lateinit var report_name:String
   // lateinit var reportData:ReportData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details1)
        ETName = findViewById(R.id.name)
        age = findViewById(R.id.age)
        gender = findViewById(R.id.textInputLayout2)
        report=findViewById(R.id.report)
//        if (intent.hasExtra("data")) {
//             reportData = (intent.getSerializableExtra("data") as? ReportData)!!
//            if (reportData != null) {
//                setData()
//            } else {
//                // Handle the case where data is null
//            }
//        } else {
//            report_name= intent.getStringExtra("report")!!
//        }
        report_name= intent.getStringExtra("report")?:"1"
//        ETName = findViewById(R.id.name)
//        age = findViewById(R.id.age)
//        report_name=reportdata.person_name


        val btn_next1 = findViewById<Button>(R.id.btn_next1)
        val btn_previous1 = findViewById<Button>(R.id.btn_previous1)

        //setData()
        //getData()

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
                "1"
             else
                "2"

            if(name.isNotEmpty()&&(selectedGender.equals("Male")||selectedGender.equals("Female"))&&age.isNotEmpty()){
                if(Integer.parseInt(age)>100){
                    Toast.makeText(this, "age must be less than 100 year", Toast.LENGTH_SHORT).show()
                }else{
                   val intent=Intent(this, DetailsActivity3::class.java)
                   // reportData=ReportData(person_name = name, check_lost = report_name,age=age,null,null,null,null,null, gender = x,null,null,null)
                    //Toast.makeText(this, reportData.person_name, Toast.LENGTH_SHORT).show()
                   // intent.putExtra("data",reportData)
                    intent.putExtra("name",name)
                intent.putExtra("gender",x)
                intent.putExtra("age",age)
                intent.putExtra("report", report_name)
                 startActivity(intent)
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
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
//            val name = data?.getStringExtra("name")
//            val age1=data?.getStringExtra("age")
//            val gender1=data?.getIntExtra("gender",1)
//            report_name=data?.getStringExtra("report")!!
//            ETName.setText(name)
//            age.setText(age1)
//            gender.setSelection(gender1!!)
//        }
//    }


//    fun setData(){
//        if(reportData?.person_name!=null)
//            ETName.setText(reportData?.person_name)
//        if(reportData?.age!=null)
//            age.setText(reportData?.age)
//        if(reportData?.gender!=null)
//            gender.setSelection(reportData?.gender!!)
//        if(reportData?.check_lost!=null)
//            report_name= reportData?.check_lost!!
//    }
}
