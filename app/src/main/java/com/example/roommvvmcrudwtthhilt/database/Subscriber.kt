package com.example.roommvvmcrudwtthhilt.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_table")
data class Subscriber(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id:Int,

    @ColumnInfo(name = "Name")
    var name: String,

    @ColumnInfo(name = "Email")
    var email: String
)