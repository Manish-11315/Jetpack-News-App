package com.mysecondapp.newsapp.data.ApiService

import com.mysecondapp.newsapp.data.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.mysecondapp.newsapp.util.key
import retrofit2.Response

interface apicall {

    @GET("top-headlines")
    suspend fun getheadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = key.apikey
    ): ApiResponse

    @GET("everything")
    suspend fun getEverything(
        @Query("q") q : String = "us",
        @Query("apiKey") apiKey: String = key.apikey
    ): ApiResponse



}