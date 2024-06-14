package com.example.quoteofthedayapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDataBase:RoomDatabase() {
    abstract fun quoteDao():QuoteDao
}