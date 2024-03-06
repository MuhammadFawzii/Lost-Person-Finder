package com.example.lostpeoplefinder

data class UserData(val username: String, val email: String, val password: String, val phone_number: String)

data class LoginData(val username: String, val password: String)

data class ApiResponse(val message: String?, val error: String?)
data class PersonModel(var headerText:String,val img:Int,var personName:String,var personData:String)
