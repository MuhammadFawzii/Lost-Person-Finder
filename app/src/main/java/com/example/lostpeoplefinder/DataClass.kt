package com.example.lostpeoplefinder

import java.io.Serializable
import android.location.Location
import java.io.File
import java.util.*
data class UserData(val username: String, val email: String, val password: String, val phone_number: String) :
    Serializable

data class VerifyData(val username: String, val email: String, val password: String, val phone_number: String,val code: String)

data class LoginData(val email: String, val password: String)

data class ApiResponse(val message: String?, val error: String?)
data class PersonModel(var headerText:String,val img:Int,var personName:String,var personData:String)

data class OutputModel(val img:Int, var personName:String, var personAge:Int,
                       var personGender:String, var last_date: Date, var personLastLocation:Location)

data class ReportPerson(
    val person_name: String?,
    val age: String?,
    val date_of_found: String?,
    val phone_number: String?,
    val image: File,
    val email: String?,
    val lng: String?,
    val lat: String?,
    val gender: String?

)
data class Response<T>(
    val data: T? = null,
    val message: String? = null
)

data class LostPersonResponse(
    val final_result: List<Person>?
)


data class Person(
    val person_name: String,
    val age: String,
    val date_of_lost: String,
    val phone_number: String,
    val email: String,
    val image_url: String?, // Assuming you're sending a file
    val lng: String,
    val lat: String,
    val gender: String
): Serializable
data class ResponseReport(
val person_name: String,
val age: String,
val date_of_lost: String,
val phone_number: String,
val email: String,
val lng: String,
val lat: String,
val gender: String,
val image_url: String? // Optional, if you need to display the image
)
