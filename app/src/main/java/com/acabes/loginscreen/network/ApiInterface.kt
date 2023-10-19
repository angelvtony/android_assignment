package com.acabes.loginscreen.network


import com.acabes.loginscreen.models.CreditialData
import com.acabes.loginscreen.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiInterface {
    @POST("/auth/login")
    suspend fun postData(@Body userData: CreditialData):Response<LoginResponse>


}