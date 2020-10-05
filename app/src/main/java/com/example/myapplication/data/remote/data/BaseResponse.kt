package com.example.myapplication.data.remote.data

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("errors")
    val errors: String?,
    @SerializedName("data")
    val data: T?,
    @SerializedName("message")
    val message:String?
)