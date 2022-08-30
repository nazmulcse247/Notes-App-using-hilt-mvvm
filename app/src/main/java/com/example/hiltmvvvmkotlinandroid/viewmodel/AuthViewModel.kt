package com.example.hiltmvvvmkotlinandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltmvvvmkotlinandroid.model.UserRequest
import com.example.hiltmvvvmkotlinandroid.model.UserResponse
import com.example.hiltmvvvmkotlinandroid.repository.AuthRepository
import com.example.hiltmvvvmkotlinandroid.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val authLiveData : LiveData<NetworkResult<UserResponse>>
    get() = authRepository.authLiveData

    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            authRepository.registerUser(userRequest)
        }
    }

    fun loginUser(userRequest: UserRequest) {
        viewModelScope.launch {
            authRepository.loginUser(userRequest)
        }
    }






}