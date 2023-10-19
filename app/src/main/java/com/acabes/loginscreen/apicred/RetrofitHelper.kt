package com.acabes.loginscreen.apicred

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val BASEURL = ("https://dummyjson.com/")
            fun getInstance(): Retrofit{
                return Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
}