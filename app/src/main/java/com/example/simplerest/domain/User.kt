package com.example.simplerest.domain

data class User (
    val id:Int = 0,
    val name: String,
    val lastName:String,
    val city:String,
    val thumbnail:String
)