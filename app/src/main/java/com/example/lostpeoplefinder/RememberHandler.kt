package com.example.yourapp

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaSession2Service.MediaNotification
import android.util.Log
import com.example.lostpeoplefinder.API.APIServies
import com.example.lostpeoplefinder.API.RetrofitClient
import com.example.lostpeoplefinder.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RememberHandler private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val apiServices: APIServies = RetrofitClient.instance

    companion object {
        private const val PREFS_NAME = "user_prefs"
        private const val KEY_ID = "user_id"
        private const val KEY_EMAIL = "user_email"
        private const val KEY_PASSWORD = "user_pass"
        private const val KEY_UN= "user_name"
        private const val KEY_LOCATION = "location"
        private const val KEY_PN = "phone_number"
        private const val KEY_LONG = "longitude"
        private const val KEY_LAT = "latitude"
        private const val KEY_CITY = "city"
        private const val KEY_NOTIFY = "notification_enabled"
        private const val KEY_TOKEN = "token"
        private const val KEY_TAG = "HandlerTAG"

        @Volatile
        private var INSTANCE: RememberHandler? = null

        fun getInstance(context: Context): RememberHandler {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RememberHandler(context.applicationContext).also { INSTANCE = it }
            }
        }
    }


       fun saveLocation(loc:String){
        sharedPreferences.edit().apply {
            putString(KEY_LOCATION, loc)
            apply()
        }

    }

    fun setUserId(id:Int) {
        sharedPreferences.edit().apply {
            putInt(KEY_ID, id)
            apply()
        }
    }

    fun setUserEmail(email:String) {
        sharedPreferences.edit().apply {
            putString(KEY_EMAIL, email)
            apply()
        }
    }
    fun setUserName(un:String){
        sharedPreferences.edit().apply {
            putString(KEY_UN, un)
            apply()
        }
    }


    fun setUserLocation(loc:String ) {
        sharedPreferences.edit().apply {
            putString(KEY_LOCATION, loc)
            apply()
        }

    }


    fun setUserPhone(pn:String){
        sharedPreferences.edit().apply {
            putString(KEY_PN, pn)
            apply()
        }
    }

    fun setUser_Long_itutde(long:String) {
        sharedPreferences.edit().apply {
            putString(KEY_LONG, long)
            apply()
        }

    }
    fun setUser_Lat_itude(lat:String) {
        sharedPreferences.edit().apply {
            putString(KEY_LAT,lat)
            apply()
        }

    }
    fun setUserToken(token:String) {
        sharedPreferences.edit().apply {
            putString(KEY_TOKEN,token)
            apply()
        }

    }

    fun setUserCity(city:String){
        sharedPreferences.edit().apply {
            putString(KEY_CITY, city)
            apply()
        }

    }

    fun setUserNotification(notification: Int) {
        sharedPreferences.edit().apply {
            putInt(KEY_NOTIFY, notification)
            apply()
        }

    }




    fun getUserId(): Int {
        return sharedPreferences.getInt(KEY_ID, -1)
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString(KEY_EMAIL, null)
    }
    fun getUserName(): String? {
        return sharedPreferences.getString(KEY_UN, null)
    }


    fun getUserPassword(): String? {
        return sharedPreferences.getString(KEY_PASSWORD, null)
    }
    fun getUserLocation(): String? {
        return sharedPreferences.getString(KEY_LOCATION, null)

    }
    fun getUserPhone(): String? {
        return sharedPreferences.getString(KEY_PN, null)

    }
    fun getUser_Long_itutde(): String? {
        return sharedPreferences.getString(KEY_LONG, null)

    }
    fun getUser_Lat_itude(): String? {
        return sharedPreferences.getString(KEY_LAT, null)
    }
    fun getUserCity(): String? {
        return sharedPreferences.getString(KEY_CITY, null)
    }

    fun getUserNotification(): Int {
        return sharedPreferences.getInt(KEY_NOTIFY,-1 )
    }


    fun clearUserData() {
        sharedPreferences.edit().apply {
            remove(KEY_EMAIL)
            remove(KEY_UN)
            remove(KEY_PN)
            remove(KEY_CITY)
            remove(KEY_LAT)
            remove(KEY_LONG)
            remove(KEY_TOKEN)
            remove(KEY_NOTIFY)
            apply()
        }
    }
    fun clearUserLocation() {
        sharedPreferences.edit().apply {
            remove(KEY_LOCATION)
            apply()
        }
    }
    override fun toString(): String {
        return "RememberHandler(userId=${getUserId()}, email=${getUserEmail()}, username=${getUserName()}, location=${getUserLocation()}, phoneNumber=${getUserPhone()}, longitude=${getUser_Long_itutde()}, latitude=${getUser_Lat_itude()}, city=${getUserCity()}, notificationEnabled=${getUserNotification()}, token=${sharedPreferences.getString(KEY_TOKEN, null)})"
    }




    fun getUserDetails(userId: Int) {
        apiServices.getUser(userId).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        // Handle the user object
                        Log.d(KEY_TAG, "User: ${it.username}")
                    }
                } else {
                    Log.e(KEY_TAG, "Failed to get user details, response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e(KEY_TAG, "Error: ${t.message}")
            }
        })
    }
}
