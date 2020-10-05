package com.example.myapplication.data.remote.client


import com.example.myapplication.data.repository.user.IUserRepository
import org.koin.core.KoinComponent

class KinderClient(private val iKinderClientBuilder: IKinderClientBuilder) : KoinComponent,
    IKinderClient {


    override fun getUserControllerRepository(): IUserRepository {
        return iKinderClientBuilder.getRetrofit().create(IUserRepository::class.java)
    }

}