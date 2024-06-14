package com.example.quoteofthedayapp.utils

import com.example.quoteofthedayapp.data.QuoteData

sealed class HomeScreenState{
    object Loading : HomeScreenState()
    data class Success(val quote: QuoteData?) : HomeScreenState()
    data class Error(val message: String) : HomeScreenState()
}