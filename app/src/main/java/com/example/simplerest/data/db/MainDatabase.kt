package com.example.simplerest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplerest.data.dao.UserDao
import com.example.simplerest.data.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class MainDatabase : RoomDatabase(){
    abstract fun getDao(): UserDao
}