package com.example.myapplication.data.remote.client

import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface IKinderClientBuilder {
    fun getClient(): OkHttpClient
    fun getBuilder(): Retrofit.Builder
    fun getRetrofit(): Retrofit
}