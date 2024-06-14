package com.example.quoteofthedayapp.data.api

import com.example.quoteofthedayapp.data.QuoteApiResponse

import retrofit2.http.GET

interface QuoteApiServices {
    @GET("random")
    suspend fun getRandomApiQuote():QuoteApiResponse
}