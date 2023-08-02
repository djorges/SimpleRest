package com.example.simplerest.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplerest.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){

    fun getUser(){
        viewModelScope.launch( Dispatchers.IO){
            val user = userRepository.getNewUser()

            Log.d("MY_APP", user.toString())
        }
    }
}