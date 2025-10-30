package com.mysecondapp.newsapp.data.ApiBuilder

import com.mysecondapp.newsapp.data.ApiService.apicall
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object buildapi {
    fun retrofitobject(): apicall {
        return Retrofit.Builder().client(OkHttpClient.Builder().build()).baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create<apicall>()
    }
}