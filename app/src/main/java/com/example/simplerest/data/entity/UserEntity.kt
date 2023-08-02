package com.example.simplerest.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "lastName")
    val lastName: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
)