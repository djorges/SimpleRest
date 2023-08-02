package com.example.simplerest.di

import android.content.Context
import androidx.room.Room
import com.example.simplerest.data.api.ApiService
import com.example.simplerest.data.dao.UserDao
import com.example.simplerest.data.db.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule{

    @Singleton
    @Provides
    fun provideApiService(): ApiService{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMainDatabase(
        @ApplicationContext context: Context
    ): MainDatabase{
        return Room.databaseBuilder( context, MainDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(
        mainDatabase: MainDatabase
    ): UserDao {
        return mainDatabase.getDao()
    }

    private const val DB_NAME = "MainDB"
    private const val BASE_URL = "https://randomuser.me/api/"
}