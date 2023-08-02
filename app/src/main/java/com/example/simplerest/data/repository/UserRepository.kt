package com.example.simplerest.data.repository

import com.example.simplerest.data.api.ApiService
import com.example.simplerest.data.dao.UserDao
import com.example.simplerest.data.db.MainDatabase
import com.example.simplerest.data.entity.UserEntity
import com.example.simplerest.domain.User
import com.example.simplerest.domain.toUser
import com.example.simplerest.domain.toUserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserRepository {
    suspend fun getNewUser(): User
    suspend fun deleteUser(user:User)
    fun getAllUsers(): Flow<List<User>>
}


class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
): UserRepository {

    override suspend fun getNewUser(): User {
        val name = apiService.getUserName().results[0].name!!
        val location = apiService.getUserLocation().results[0].location!!
        val picture = apiService.getUserPicture().results[0].picture!!

        val entity = UserEntity(
            name = name.first,
            lastName = name.last,
            city = location.city,
            thumbnail = picture.thumbnail
        )

        //Insert new user
        userDao.insert(entity)

        return entity.toUser()
    }

    override suspend fun deleteUser(user: User) = userDao.delete( user.toUserEntity() )

    override fun getAllUsers(): Flow<List<User>> = userDao.getAll().map {
        it.map { entity ->
            entity.toUser()
        }
    }
}