package com.example.lostpeoplefinder

data class UserData(val username: String, val email: String, val password: String, val phoneNumber: String)

data class LoginData(val username: String, val password: String)

data class ApiResponse(val message: String?, val error: String?)