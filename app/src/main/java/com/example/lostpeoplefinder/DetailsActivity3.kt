package com.example.lostpeoplefinder

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class DetailsActivity3 : AppCompatActivity() {
    lateinit var date:TextView
    lateinit var city:EditText
    lateinit var report:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details4)
        Log.d("d3",ReportDataBuffer.toString())
        //Log.d("testPrev",ReportDataBuffer.getPersonName().toString()+ReportDataBuffer.getAge().toString()+ReportDataBuffer.getGender().toString())
////        val report_name = intent.getStringExtra("report")
////        val name=intent.getStringExtra("name")
////        val age=intent.getStringExtra("age")
////        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
//        val gender=intent.getStringExtra("gender")
        val btn_next4=findViewById<Button>(R.id.bt_next4)
        city=findViewById<EditText>(R.id.city)
        report=findViewById(R.id.textView6)
        val btn_previous4=findViewById<Button>(R.id.btn_previous4)
        val calendarIcon = findViewById<ImageView>(R.id.calender)
         date=findViewById<TextView>(R.id.et_last_Data)
        val dateConstrain=findViewById<ConstraintLayout>(R.id.constraintLayout9)
        getData()
//        Toast.makeText(this@DetailsActivity3, report_name.toString(), Toast.LENGTH_SHORT)
//            .show()
        btn_next4.setOnClickListener {
            val date=date.text.toString()
            val lang="160"
            val lat="120"
            val cityName=city.text.toString()
            if(date.isNotEmpty()&&cityName.isNotEmpty()) {
//                val intent = Intent(this, DetailsActivity4::class.java)
//                intent.putExtra("name", name)
//                intent.putExtra("gender", gender)
//                intent.putExtra("age", age)
//                intent.putExtra("date",date)
//                intent.putExtra("lang", lang)
//                intent.putExtra("lat",lat)
//                intent.putExtra("report", report_name)
//                intent.putExtra("city",cityName)

                ReportDataBuffer.updateDateOfLost(date)
                ReportDataBuffer.updateLng(lang)
                ReportDataBuffer.updateLat(lat)
                ReportDataBuffer.updateCity(cityName)





                startActivity(Intent(this, DetailsActivity4::class.java))
            }
            else{
                Toast.makeText(this, "Fill all data.", Toast.LENGTH_SHORT).show()
            }
        }
        dateConstrain.setOnClickListener {
            showDatePicker()
        }
        btn_previous4.setOnClickListener {
           val intent= Intent(this,DetailsActivity1::class.java)
//            intent.putExtra("name",name)
//            intent.putExtra("age",age)
//            intent.putExtra("gender",gender)
//            intent.putExtra("report",report_name)
            startActivity(intent)
        }
        calendarIcon.setOnClickListener {
            showDatePicker()
        }
        val enterLocationTextView = findViewById<TextView>(R.id.tv_enter_location)
        enterLocationTextView.setOnClickListener { // Open a new page or handle Google Maps here
            // For example, you can start a new activity
            startActivity(Intent(this@DetailsActivity3, MapActivity::class.java))
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
                val new="$selectedYear/${selectedMonth+1}/$dayOfMonth"
                val date1 = convertDateFormat(new)
                date.text=new
                Toast.makeText(this, new, Toast.LENGTH_SHORT).show()

            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }

    fun convertDateFormat(inputDate: String): String {
        // Split the input date by "/"
        val parts = inputDate.split("/")

        // Extract day, month, and year from the input date
        val day = parts[0]
        val month = parts[1]
        val year = parts[2]

        // Create a Calendar instance
        val calendar = Calendar.getInstance()

        // Set the year, month, and day of month in the Calendar instance
        calendar.set(Calendar.YEAR, year.toInt())
        calendar.set(Calendar.MONTH, month.toInt() - 1) // Calendar.MONTH is zero-based
        calendar.set(Calendar.DAY_OF_MONTH, day.toInt())

        // Format the date in "yyyy-MM-dd" format
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(calendar.time)
    }

    fun getData(){
        val cityName:String=intent.getStringExtra("city")?:""
        if(cityName!=null)
            city.setText(cityName)
        val Date:String=intent.getStringExtra("date")?:""
        if(Date!=null)
            date.setText(Date)
        val genderText:String=intent.getStringExtra("gender")?:"1"
        val reportNum:String=intent.getStringExtra("report")?:""
        if(reportNum!=null)
            if(reportNum.equals("1"))
                report.text="Found Person Details"
    }
}