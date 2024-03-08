package com.example.lostpeoplefinder

import android.location.Location
import java.util.*

data class UserData(val username: String, val email: String, val password: String, val phone_number: String)

data class LoginData(val username: String, val password: String)

data class ApiResponse(val message: String?, val error: String?)
data class PersonModel(var headerText:String,val img:Int,var personName:String,var personData:String)

data class OutputModel(val img:Int, var personName:String, var personAge:Int,
                       var personGender:String, var last_date: Date, var personLastLocation:Location)
