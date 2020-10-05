package com.example.myapplication.di

import com.colan.kindercare.data.local.sharedPreference.ISharedPreferenceService
import com.colan.kindercare.data.local.sharedPreference.SharedPreferenceImp
import com.example.myapplication.data.remote.client.*
import com.example.myapplication.data.repository.user.IUserControllerRepository
import com.example.myapplication.data.repository.user.UserControllerRepository
import okhttp3.Interceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

class KoinCoreModule {
    val koinCoreModule = module {
        single<ISharedPreferenceService>(definition = { SharedPreferenceImp(androidApplication()) })
        single<Interceptor> { AuthTokenInterceptor(get()) }
        single<IKinderClientBuilder> { KinderClientBuilder(get(), get()) }
        single<IKinderClient> { KinderClient(get()) }
        single<IUserControllerRepository> { UserControllerRepository(get<IKinderClient>().getUserControllerRepository()) }
    }
}