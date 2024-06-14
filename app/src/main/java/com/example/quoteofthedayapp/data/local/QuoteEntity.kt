package com.example.quoteofthedayapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_quote")
data class QuoteEntity(
    @PrimaryKey val id:String,
    val author:String,
    val content:String,
    val hashTag:String
)
