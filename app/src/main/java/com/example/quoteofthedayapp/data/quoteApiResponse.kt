package com.example.quoteofthedayapp.data

data class QuoteApiResponse(
    val _id: String,
    val content: String,
    val author: String,
    val tags:List<String>,
    val authorSlug: String,
    val length: Int,
    val dateAdded: String,
    val dateModified: String
)

