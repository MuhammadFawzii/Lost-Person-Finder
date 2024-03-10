package com.example.lostpeoplefinder

import java.io.Serializable
import android.location.Location
import java.util.*
import kotlin.collections.ArrayList

data class UserData(val username: String, val email: String, val password: String, val phone_number: String) :
    Serializable

data class VerifyData(val username: String, val email: String, val password: String, val phone_number: String,val code: String)

data class LoginData(val email: String, val password: String)

data class ApiResponse(val message: String?, val error: String?)
data class PersonModel(var headerText:String,val img:Int,var personName:String,var personData:String)

data class OutputModel(val img:Int, var personName:String, var personAge:Int,
                       var personGender:String, var last_date: Date, var personLastLocation:Location)

data class ParentModel(var title:String, var list:ArrayList<OutputModel>)

