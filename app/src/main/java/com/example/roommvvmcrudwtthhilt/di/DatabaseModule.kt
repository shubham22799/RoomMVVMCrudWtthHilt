package com.example.roommvvmcrudwtthhilt.di

import android.content.Context
import androidx.room.Room
import com.example.roommvvmcrudwtthhilt.database.Dao
import com.example.roommvvmcrudwtthhilt.database.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext appContext: Context): MyDatabase {
        return Room.databaseBuilder(
            appContext,
            MyDatabase::class.java,
            "my_data_database"
        ).build()
    }

    @Provides
    fun providesDao(myDatabase: MyDatabase): Dao{
        return myDatabase.MyDao()
    }
}