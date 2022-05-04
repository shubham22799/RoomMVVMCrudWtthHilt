package com.example.roommvvmcrudwtthhilt.repository

import com.example.roommvvmcrudwtthhilt.database.Dao
import com.example.roommvvmcrudwtthhilt.database.MyDatabase
import com.example.roommvvmcrudwtthhilt.database.Subscriber
import kotlinx.coroutines.flow.Flow
import java.lang.reflect.Constructor
import javax.inject.Inject

class SubscriberRepositoryImp @Inject constructor(private val dao: Dao) : SubscriberRepository {
    override fun getAll(): Flow<List<Subscriber>> {
        return dao.getAll()
    }

    override suspend fun insert(subscriber: Subscriber): Long {
        return dao.insert(subscriber)
    }

    override suspend fun update(subscriber: Subscriber): Int {
        return dao.update(subscriber)
    }

    override suspend fun delete(subscriber: Subscriber): Int {
        return dao.delete(subscriber)
    }

    override suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }

}