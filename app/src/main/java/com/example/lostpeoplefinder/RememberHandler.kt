package com.example.yourapp

import android.content.Context
import android.content.SharedPreferences
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
        private const val KEY_EMAIL = "user_email"
        private const val KEY_PASSWORD = "user_pass"
        private const val KEY_ID = "user_id"
        private const val KEY_TAG = "HandlerTAG"

        @Volatile
        private var INSTANCE: RememberHandler? = null

        fun getInstance(context: Context): RememberHandler {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RememberHandler(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    fun saveUserId(id: Int) {
        sharedPreferences.edit().apply {
            putInt(KEY_ID, id)
            apply()
        }
    }

    fun saveUserLoginData(email: String, password: String) {
        sharedPreferences.edit().apply {
            putString(KEY_EMAIL, email)
            putString(KEY_PASSWORD, password)
            apply()
        }
    }

    fun getUserId(): Int {
        return sharedPreferences.getInt(KEY_ID, -1)
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString(KEY_EMAIL, null)
    }

    fun getUserPassword(): String? {
        return sharedPreferences.getString(KEY_PASSWORD, null)
    }

    fun clearUserLoginData() {
        sharedPreferences.edit().apply {
            remove(KEY_EMAIL)
            remove(KEY_PASSWORD)
            apply()
        }
    }

    fun clearUserId() {
        sharedPreferences.edit().apply {
            remove(KEY_ID)
            apply()
        }
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
