package com.example.simplerest.di

import com.example.simplerest.data.repository.UserRepository
import com.example.simplerest.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Singleton
    @Binds
    abstract fun bindUserRepository(
        repoImpl: UserRepositoryImpl
    ):UserRepository
}
