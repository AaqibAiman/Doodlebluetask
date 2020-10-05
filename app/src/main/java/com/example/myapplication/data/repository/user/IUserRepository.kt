package com.example.myapplication.data.repository.user

import com.example.myapplication.data.remote.data.BaseResponse
import com.example.myapplication.data.remote.data.response.UserProfileResponse
import retrofit2.Call
import retrofit2.http.*

interface IUserRepository {

    @GET("assets")
    fun getUserProfile(): Call<BaseResponse<ArrayList<UserProfileResponse>>>



}