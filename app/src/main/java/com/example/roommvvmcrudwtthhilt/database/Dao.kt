package com.example.roommvvmcrudwtthhilt.database

import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    suspend fun insert(subscriber: Subscriber):Long

    @Delete
    suspend fun delete(subscriber: Subscriber):Int

    @Update
    suspend fun update(subscriber: Subscriber):Int

    @Query("DELETE FROM my_table")
    suspend fun deleteAll():Int

    @Query("SELECT * FROM my_table")
    fun getAll(): Flow<List<Subscriber>>

}