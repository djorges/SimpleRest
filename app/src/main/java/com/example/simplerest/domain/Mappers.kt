package com.example.simplerest.domain

import com.example.simplerest.data.entity.UserEntity

fun UserEntity.toUser(): User {
    return User(
        id = id,
        name = name,
        lastName = lastName,
        city = city,
        thumbnail = thumbnail
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        lastName = lastName,
        city = city,
        thumbnail = thumbnail
    )
}