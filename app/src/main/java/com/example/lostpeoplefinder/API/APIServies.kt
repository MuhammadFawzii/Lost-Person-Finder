package com.example.lostpeoplefinder.API

import com.example.lostpeoplefinder.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


public interface APIServies {
//    @POST("register")
//    fun registerUser(@Body email: String): Call<ApiResponse>
@POST("/register")
open fun registerUser(@Body registerRequest: RegisterRequest?): Call<ApiResponse>
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
        @Field("code") code: String,
        @Field("city") city: String,
        @Field("token") token: String,
        @Field("lng") lng: Double,
        @Field("lat") lat: Double,
        @Field("notifications") isEnabled: Int,

        ): Call<ApiResponse>


    @FormUrlEncoded
    @POST("forgot_password")
    fun forgotPassword(@Field("email") email: String): Call<ApiResponse>
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
        @Part("gender") gender: RequestBody,
        @Part("id") id:RequestBody
    ): Call<FindResponse>





    @Multipart
    @POST("/search")
    fun send_find(
        @Part("check_lost") check_lost: RequestBody,
        @Part("person_name") person_name: RequestBody,
        @Part("age") age: RequestBody,
        @Part("date") date_of_lost: RequestBody,
        @Part("phone_number") phone_number: RequestBody,
        @Part("email") email: RequestBody,
        @Part image: MultipartBody.Part,
        @Part("city") city: RequestBody,
        @Part("lng") lng: RequestBody,
        @Part("lat") lat: RequestBody,
        @Part("gender") gender: RequestBody,
        @Part("notes") notes: RequestBody,
        @Part("user_id") id:RequestBody
    ): Call<FindResponse>

    @GET("/home_lost")
    fun getLostPeople(): Call<Map<String, Person>>
    @GET("/reports/{user_id}")
    fun getUserReports(@Path("user_id") userId: Int): Call<Map<String, Person>>
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
        @Field("lng") lng: Double,
        @Field("lat") lat: Double,
        @Field("city") city: String,
        @Field("notifications") notifications: String
    ): Call<Void> // Modify return type based on your response

}
