package com.example.lostpeoplefinder.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitClient {
    //http://127.0.0.1:5000/
    //http://192.168.0.104:5000/
    //http://192.168.209.1:5000/
    // http://10.0.0.63:5000

    private const val BASE_URL = "http://192.168.209.1:5000/"


    val instance: APIServies by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(APIServies::class.java)

    }

}