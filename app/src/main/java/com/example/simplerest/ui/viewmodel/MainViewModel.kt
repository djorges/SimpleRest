package com.example.simplerest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplerest.data.repository.UserRepository
import com.example.simplerest.domain.User
import com.example.simplerest.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){
    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading(true))
    val state: StateFlow<UiState> = _state

    val users = userRepository.getAllUsers()
        .onEach {
            _state.emit(UiState.Loading(true))

            delay(1000L)

            _state.emit(UiState.Loading(false))
        }
        .catch { throwable ->  _state.emit(UiState.Error(throwable)) }

    fun addUser(){
        viewModelScope.launch( Dispatchers.IO){
            userRepository.getNewUser()
        }
    }

    fun deleteUser(user:User){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.deleteUser(user)
        }
    }
}