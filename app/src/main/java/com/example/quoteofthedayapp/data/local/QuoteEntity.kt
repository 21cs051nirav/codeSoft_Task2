package com.example.quoteofthedayapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_quote")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id:Int=0,
    val author:String,
    val content:String,
    val hashTag:String
)
