package com.example.event_booking_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    //val baseUrl = "https://healthy-slippers-clam.cyclic.app/events/"
    val baseUrl = "http://192.168.1.4:3000/events/"

    /**
     * Returns an instance of the Retrofit library configured with the base URL and Gson converter factory.
     *
     * @return A Retrofit instance.
     */
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}