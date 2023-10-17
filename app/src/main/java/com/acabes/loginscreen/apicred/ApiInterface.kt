package com.acabes.loginscreen.apicred


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiInterface {
    @POST("/auth/login")
    suspend fun postData(@Body userData: Cred):Response<LoginResponse>


}