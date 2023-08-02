package com.example.simplerest.data.api

import com.example.simplerest.data.dto.ApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/?inc=name")
    suspend fun getUserName(): ApiResponse

    @GET("/?inc=location")
    suspend fun getUserLocation(): ApiResponse

    @GET("/?inc=picture")
    suspend fun getUserPicture(): ApiResponse
}