package com.example.myapplication.data.repository.user

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.remote.data.BaseResponse
import com.example.myapplication.data.remote.data.Resource
import com.example.myapplication.data.repository.BaseRespository
import com.example.myapplication.data.remote.data.response.UserProfileResponse
import com.example.myapplication.data.repository.user.IUserControllerRepository
import com.example.myapplication.data.repository.user.IUserRepository
import org.koin.core.KoinComponent

class UserControllerRepository(private val iUserControllerRepository: IUserRepository) :
    KoinComponent, IUserControllerRepository, BaseRespository() {

    override fun getUserProfile(
        response: MutableLiveData<Resource<BaseResponse<ArrayList<UserProfileResponse>>>>
    ) {
        iUserControllerRepository.getUserProfile().enqueue(getCallback(response))
    }


}