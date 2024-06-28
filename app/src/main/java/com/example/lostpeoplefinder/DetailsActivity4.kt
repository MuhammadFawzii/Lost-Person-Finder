package com.example.lostpeoplefinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast

class DetailsActivity4 : AppCompatActivity() {
  //  lateinit var reportData:ReportData
    lateinit var eNumber:EditText
    lateinit var eEmail:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details5)
        eNumber=findViewById<EditText>(R.id.number)
        eEmail=findViewById<EditText>(R.id.email)
//        if (intent.hasExtra("data")) {
//            reportData = (intent.getSerializableExtra("data") as? ReportData)!!
//            if (reportData != null) {
//                setData()
//            } else {
//                // Handle the case where data is null
//            }
//        } else {
//
//        }
        val report_name = intent.getStringExtra("report")
        val name=intent.getStringExtra("name")
        val age=intent.getStringExtra("age")
        val gender=intent.getStringExtra("gender")
        val date=intent.getStringExtra("date")
        val lang=intent.getStringExtra("lng")
        val lat=intent.getStringExtra("lat")
        val cityName=intent.getStringExtra("city")

//        val report_name = reportData?.check_lost
//        val name=reportData?.person_name
//        val age=reportData?.age
//        val gender=reportData?.gender
//        val date=reportData?.date_of_lost
//        val lang=reportData?.lng
//        val lat=reportData?.lat
//        val cityName=reportData?.city
        val btn_next5=findViewById<Button>(R.id.bt_next5)
        val btn_previous5=findViewById<Button>(R.id.bt_previous5)

        //Toast.makeText(this@DetailsActivity4, reportData.person_name.toString(), Toast.LENGTH_SHORT)
           // .show()
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val phoneNumberPattern = "\\d{11}"
        btn_next5.setOnClickListener {
            val number=eNumber.text.toString()
            val email=eEmail.text.toString()
            val isEmailValid = email.matches(emailPattern.toRegex())
            val isPhoneNumberValid = number.matches(phoneNumberPattern.toRegex())
            if (!isEmailValid) {
                Toast.makeText(this, "Email not valid \n Should be like example@gmail.com", Toast.LENGTH_LONG).show()
            }
            if (!isPhoneNumberValid) {
                Toast.makeText(this, "Phone Number not valid \n Should be 11 char", Toast.LENGTH_LONG).show()
            }
            if (isEmailValid && isPhoneNumberValid) {
                val intent=Intent(this,DetailsActivity5::class.java)
                intent.putExtra("name", name)
                intent.putExtra("gender", gender)
                intent.putExtra("age", age)
                intent.putExtra("date",date)
                intent.putExtra("number",number)
                intent.putExtra("email",email)
                intent.putExtra("lng", lang)
                intent.putExtra("lat",lat)
                intent.putExtra("report", report_name)
                intent.putExtra("city",cityName)
               // reportData=ReportData(person_name = reportData.person_name, check_lost = reportData.check_lost,age=reportData.age, date_of_lost = reportData.date_of_lost, phone_number = number,email= email, lat = reportData.lat, lng = reportData.lng, gender = reportData.gender, image_url = null, city = reportData.city, notes = null)

                //intent.putExtra("data",reportData)
                startActivity(intent)

            }
        }
        btn_previous5.setOnClickListener {
            val intent=Intent(this,DetailsActivity3::class.java)
//            resultIntent.putExtra("name", name)
//            resultIntent.putExtra("age", age)
//            resultIntent.putExtra("date", date)
//            resultIntent.putExtra("gender", gender)
//            resultIntent.putExtra("lat", lat)
//            resultIntent.putExtra("lng", lang)
//            resultIntent.putExtra("city", cityName)
//            resultIntent.putExtra("report", report_name)
            //intent.putExtra("data",reportData)
            startActivity(intent)

//            var intent=Intent(this,DetailsActivity3::class.java)
//            intent.putExtra("name", name)
//            intent.putExtra("gender", gender)
//            intent.putExtra("age", age)
//            intent.putExtra("date",date)
//            intent.putExtra("lang", lang)
//            intent.putExtra("lat",lat)
//            intent.putExtra("report", report_name)
//            intent.putExtra("city",cityName)
//            startActivity(intent)

        }

    }

//    private fun setData() {
//        if(reportData.phone_number!=null)
//            eNumber.setText(reportData.phone_number)
//        if(reportData.email!=null)
//            eEmail.setText(reportData.email)
//    }
}