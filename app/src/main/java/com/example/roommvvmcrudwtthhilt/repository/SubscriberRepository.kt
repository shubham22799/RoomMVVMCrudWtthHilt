package com.example.roommvvmcrudwtthhilt.repository

import com.example.roommvvmcrudwtthhilt.database.Subscriber
import kotlinx.coroutines.flow.Flow

interface SubscriberRepository {

    fun getAll(): Flow<List<Subscriber>>

    suspend fun insert(subscriber: Subscriber): Long

    suspend fun update(subscriber: Subscriber): Int

    suspend fun delete(subscriber: Subscriber): Int

    suspend fun deleteAll():Int
}