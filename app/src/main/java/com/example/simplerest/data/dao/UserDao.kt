package com.example.simplerest.data.dao

import androidx.room.*
import com.example.simplerest.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id DESC")
    fun getAll(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user:UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)
}