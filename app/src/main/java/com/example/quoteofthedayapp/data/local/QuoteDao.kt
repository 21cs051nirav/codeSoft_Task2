package com.example.quoteofthedayapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: QuoteEntity)

    @Query("select * from favorite_quote")
    suspend fun getAllQuote():List<QuoteEntity>

    @Query("select count(*) from favorite_quote where id==:id")
    suspend fun findInDB(id:String):Int

    @Query("delete from favorite_quote where id==:id")
    suspend fun removeFromDB(id: String)
    @Query("delete from favorite_quote")
    suspend fun deleteAllQuote()
}