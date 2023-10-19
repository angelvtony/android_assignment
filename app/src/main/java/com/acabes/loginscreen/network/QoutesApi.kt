package com.acabes.loginscreen.network

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;


interface QuotesApi {
    @GET("v2/quotes")
    suspend fun getQuotes() : Response<List<String>>
}