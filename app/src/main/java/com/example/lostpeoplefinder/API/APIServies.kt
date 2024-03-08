package com.example.lostpeoplefinder.API

import com.example.lostpeoplefinder.ApiResponse
import com.example.lostpeoplefinder.LoginData
import com.example.lostpeoplefinder.UserData
import com.example.lostpeoplefinder.VerifyData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

public interface APIServies {
    @POST("register")
    fun registerUser(@Body userData: UserData): Call<ApiResponse>

    @POST("login")
    fun loginUser(@Body loginData: LoginData): Call<ApiResponse>


    @POST("logout")
    fun logoutUser(): Call<ApiResponse>
    @FormUrlEncoded
    @POST("verify_code")
    fun verifyCode(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("phone_number") phoneNumber: String,
        @Field("email") email: String,
        @Field("code") code: String
    ): Call<ApiResponse>
    @FormUrlEncoded
    @POST("forgot_password")
    fun forgotPassword(@Field("email") email: String,): Call<ApiResponse>
    @FormUrlEncoded
    @POST("verify_reset_code_password")
    fun verifyResetPassword(
        @Field("email") email: String,
        @Field("code") code: String
    ): Call<ApiResponse>

    @FormUrlEncoded
    @POST("set_new_password")
    fun setNewPassword(@Field("email") email: String,@Field("new_password") newPassword: String): Call<ApiResponse>
}
