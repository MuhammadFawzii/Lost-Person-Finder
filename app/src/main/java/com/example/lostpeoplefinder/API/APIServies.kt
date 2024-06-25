package com.example.lostpeoplefinder.API

import com.example.lostpeoplefinder.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*
import java.io.File

public interface APIServies {
    @POST("register")
    fun registerUser(@Body userData: UserData): Call<ApiResponse>

    @POST("login")
    fun loginUser(@Body loginData: LoginData): Call<LoginResponse>


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

  /*  @FormUrlEncoded
    @POST("/find")
    fun reportLostPerson(
        @Field("person_name") person_name: String?,
        @Field("age") age: String?,
        @Field("phone_number") phone_number: String?,
        @Field("email") email: String?,
        @Field("date_of_found") date: String?,
        @Field("lat") lat: String?,
        @Field("lng") lng: String?,
        @Field("gender") gender: String?,
        @Field("image") image: File,
    ): Call<ApiResponse>*/

    @Multipart
    @POST("/lost")
    fun sendPersonData(
        @Part("person_name") person_name: RequestBody,
        @Part("age") age: RequestBody,
        @Part("date") date_of_lost: RequestBody,
        @Part("phone_number") phone_number: RequestBody,
        @Part("email") email: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("lng") lng: RequestBody,
        @Part("lat") lat: RequestBody,
        @Part("gender") gender: RequestBody
    ): Call<FindResponse>





    @Multipart
    @POST("/find")
    fun send_find(
        @Part("person_name") person_name: RequestBody,
        @Part("age") age: RequestBody,
        @Part("date") date_of_lost: RequestBody,
        @Part("phone_number") phone_number: RequestBody,
        @Part("email") email: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("lng") lng: RequestBody,
        @Part("lat") lat: RequestBody,
        @Part("gender") gender: RequestBody
    ): Call<FindResponse>

    @GET("/home_lost")
    fun getLostPeople(): Call<Map<String, Person>>

    @GET("/home_find")
    fun getFoundPeople(): Call<Map<String, Person>>

    @GET("getuser/{id}")
    fun getUser(@Path("id") id: Int): Call<User>

    @POST("update_user")
    @FormUrlEncoded
    fun updateUser(
        @Field("id") id: String,
        @Field("username") username: String,
        @Field("phone_number") phoneNumber: String,
        @Field("lng") lng: String,
        @Field("lat") lat: String,
        @Field("city") city: String,
        @Field("notifications") notifications: String
    ): Call<Void> // Modify return type based on your response

}
