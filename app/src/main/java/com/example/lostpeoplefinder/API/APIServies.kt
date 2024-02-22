package com.example.lostpeoplefinder.API

import com.example.lostpeoplefinder.ApiResponse
import com.example.lostpeoplefinder.LoginData
import com.example.lostpeoplefinder.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

public interface APIServies {
    @POST("register")
    fun registerUser(@Body userData: UserData): Call<ApiResponse>

    @POST("login")
    fun loginUser(@Body loginData: LoginData): Call<ApiResponse>

    @POST("logout")
    fun logoutUser(): Call<ApiResponse>
}
