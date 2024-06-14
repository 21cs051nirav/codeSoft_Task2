package com.example.quoteofthedayapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL="https://api.quotable.io/"

    val api:QuoteApiServices by lazy {
       Retrofit.Builder()
           .baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(QuoteApiServices::class.java)
    }
}