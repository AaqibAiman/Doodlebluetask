package com.example.myapplication.data.remote.client

import com.example.myapplication.data.repository.user.IUserRepository

interface IKinderClient {
    fun getUserControllerRepository(): IUserRepository
}