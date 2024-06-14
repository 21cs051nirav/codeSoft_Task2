package com.example.quoteofthedayapp.utils

import android.util.Log
import com.example.quoteofthedayapp.data.QuoteApiResponse
import com.example.quoteofthedayapp.data.QuoteData
import com.example.quoteofthedayapp.data.local.QuoteEntity

fun mapToQuoteData(quote:QuoteApiResponse):QuoteData {
    Log.d("Api Response", "getRandomDailyQuote: $quote")
    return QuoteData(
        content = quote.content,
        author = quote.author,
        tags = quote.tags
    )
}

fun mapToQuoteData(quoteEntity: List<QuoteEntity>):List<QuoteData>{
    val list= mutableListOf<QuoteData>()
    quoteEntity.forEach {
        list.add(QuoteData(it.content,it.author,it.hashTag.split(",")))
    }
    return list;
}
fun mapToQuoteEntity(quote: QuoteData):QuoteEntity{
    return QuoteEntity(
        author = quote.author,
        content = quote.author,
        hashTag = quote.tags.joinToString(",")
    )
}