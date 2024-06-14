package com.example.quoteofthedayapp.repository

import android.util.Log
import com.example.quoteofthedayapp.data.QuoteData
import com.example.quoteofthedayapp.data.api.QuoteApiServices
import com.example.quoteofthedayapp.data.local.QuoteDao
import com.example.quoteofthedayapp.utils.mapToQuoteData
import com.example.quoteofthedayapp.utils.mapToQuoteEntity

class QuoteRepository(private  val apiServices: QuoteApiServices, private val quoteDao:QuoteDao) {
        suspend fun getRandomDailyQuote():QuoteData?{
            return try {
                val response=apiServices.getRandomApiQuote()
                mapToQuoteData(response)
            }catch (_:Exception){
                null
            }
        }
        suspend fun getFavouriteQuotes():List<QuoteData>{
            return mapToQuoteData(quoteDao.getAllQuote())
        }

        suspend fun addFavouriteQuote(quote: QuoteData){
            quoteDao.insert(mapToQuoteEntity( quote))
        }
//
//        suspend fun removeFavouriteQuote(quote: QuoteData){
//            try {
//                quoteDao.deleteQuote(quote)
//            }catch (e:Exception){
//                Log.d("QuoteRepository", "removeFavouriteQuote: ${e.message}")
//            }
//        }
//        suspend fun deleteAllFavouriteQuote(){
//            try {
//                quoteDao.deleteAllQuote()
//            }catch (e:Exception){
//                Log.d("QuoteRepository", "deleteAllFavouriteQuote: ${e.message}")
//            }
//        }
}

//class QuoteViewModelFactory(private val repository: QuoteRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
//        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
//            return QuoteViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//
//}