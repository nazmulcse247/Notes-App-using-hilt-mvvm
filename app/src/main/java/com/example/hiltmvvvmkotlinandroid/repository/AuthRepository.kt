package com.example.hiltmvvvmkotlinandroid.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hiltmvvvmkotlinandroid.api.UserApi
import com.example.hiltmvvvmkotlinandroid.model.UserRequest
import com.example.hiltmvvvmkotlinandroid.model.UserResponse
import com.example.hiltmvvvmkotlinandroid.utils.NetworkResult
import javax.inject.Inject

class AuthRepository @Inject constructor (private val userApi: UserApi) {

     val authMutableLiveData = MutableLiveData<NetworkResult<UserResponse>>()

     val authLiveData : LiveData<NetworkResult<UserResponse>>
     get() = authMutableLiveData

    suspend fun registerUser(userRequest: UserRequest){
        authMutableLiveData.postValue(NetworkResult.Loading())
        val response = userApi.signUp(userRequest)
        if (response.isSuccessful && response.body() != null) {
            authMutableLiveData.postValue(NetworkResult.Success(response.body()))
        }
        else if (response.errorBody() != null){
            authMutableLiveData.postValue(NetworkResult.Error("Something went to wrong"))
        }

        else {
            authMutableLiveData.postValue(NetworkResult.Error("Something went to wrong"))
        }

    }

    suspend fun loginUser(userRequest: UserRequest) {
        val response = userApi.signIn(userRequest)
        Log.d(TAG, "loginUser: "+response.body().toString())
    }
}