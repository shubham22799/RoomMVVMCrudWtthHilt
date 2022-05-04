package com.example.roommvvmcrudwtthhilt.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class MyDatabase: RoomDatabase() {
    abstract fun MyDao(): Dao
}