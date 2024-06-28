package com.example.lostpeoplefinder

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class DetailsActivity3 : AppCompatActivity() {
    private val REQUEST_CODE_A = 2
    private val REQUEST_CODE_B = 1
    lateinit var date:TextView
    lateinit var location:TextView
    lateinit var city:EditText
    lateinit var report:TextView
    var name:String?=""
    var age:String?=""
    //lateinit var reportData:ReportData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details4)
        val reportName = intent.getStringExtra("report")
         name=intent.getStringExtra("name")
         age=intent.getStringExtra("age")
        var gender=intent.getStringExtra("gender")

//        if (intent.hasExtra("data")) {
//            reportData = (intent.getSerializableExtra("data") as? ReportData)!!
//            if (reportData != null) {
//                setData()
//            } else {
//                // Handle the case where data is null
//            }
//        } else {
//            // Handle the case where the intent does not have the extra "data"
//        }
       // Toast.makeText(this, reportData.person_name, Toast.LENGTH_SHORT).show()
        location=findViewById<TextView>(R.id.tv_enter_location)
        val btn_next4=findViewById<Button>(R.id.bt_next4)
        city=findViewById<EditText>(R.id.city)
        report=findViewById(R.id.textView6)
        val btn_previous4=findViewById<Button>(R.id.btn_previous4)
        val calendarIcon = findViewById<ImageView>(R.id.calender)
         date=findViewById<TextView>(R.id.et_last_Data)
        val dateConstrain=findViewById<ConstraintLayout>(R.id.constraintLayout9)
       // getData()
//        Toast.makeText(this@DetailsActivity3, report_name.toString(), Toast.LENGTH_SHORT)
//            .show()
        btn_next4.setOnClickListener {
            val date=date.text.toString()
            val lang="31.525"
            val lat="30.552"
            val cityName=city.text.toString()
            if(date.isNotEmpty()&&cityName.isNotEmpty()) {
                val intent = Intent(this, DetailsActivity4::class.java)
                intent.putExtra("name", name)
                intent.putExtra("gender", gender)
                intent.putExtra("age", age)
                intent.putExtra("date",date)
                intent.putExtra("lng", lang)
                intent.putExtra("lat",lat)
                intent.putExtra("report", reportName)
//                reportData?.lng=lang
//                reportData?.lat=lat
//                reportData?.city=cityName
//                reportData?.date_of_lost=date
                //reportData=ReportData(person_name = reportData.person_name, check_lost = reportData.check_lost,age=reportData.age, date_of_lost = date,null,null, lat = lat, lng = lang, gender = reportData.gender, image_url = null, city = cityName, notes = null)
                //intent.putExtra("data",reportData)
                intent.putExtra("city",cityName)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Fill all data.", Toast.LENGTH_SHORT).show()
            }
        }
        dateConstrain.setOnClickListener {
            showDatePicker()
        }
        btn_previous4.setOnClickListener {
            val intent = Intent(this, DetailsActivity1::class.java)
//            val resultIntent = Intent()
//            resultIntent.putExtra("name", name)
//            resultIntent.putExtra("age", age)
//            resultIntent.putExtra("gender", gender)
//            resultIntent.putExtra("report", reportName)
//            setResult(RESULT_OK, resultIntent)
//            finish() // Go back to the previous activity
//           val intent= Intent(this,DetailsActivity1::class.java)
//            intent.putExtra("name",name)
//            intent.putExtra("age",age)
//            intent.putExtra("gender",gender)
//            intent.putExtra("report",report_name)
//
            //intent.putExtra("data",reportData)
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
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//            when (requestCode) {
//                REQUEST_CODE_A -> {
//                    name=data?.getStringExtra("name")
//                    gender= data?.getIntExtra("gender",1)
//                    age=data?.getStringExtra("age")
//                    date.setText(data?.getStringExtra("date"))
//                    location.setText(data?.getStringExtra("lat")+" "+ data?.getStringExtra("lng"))
//                    data?.getStringExtra("report")
//                    city.setText(data?.getStringExtra("city"))
//
//                }
//                REQUEST_CODE_B -> {
//                    city.setText(data?.getStringExtra("cityName"))
//                    val latitude = data?.getStringExtra("latitude")
//                    val longitude = data?.getStringExtra("longitude")
//                    location.text = latitude + " " + longitude
//                }
//            }
//        }
    //}

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${selectedMonth + 1}/$selectedYear"
                val date1 = convertDateFormat(selectedDate)
                date.text=date1

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

//    fun setData(){
//       if(reportData.date_of_lost!=null)
//           date.setText(reportData.date_of_lost)
//        if(reportData.city!=null)
//            city.setText(reportData.city)
//        if(reportData.lat!=null &&reportData.lng!=null)
//            location.setText(reportData.lat)
//    }
}