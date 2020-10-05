package com.example.myapplication

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.colan.kindercare.data.local.sharedPreference.ISharedPreferenceService
import com.example.myapplication.data.remote.data.BaseResponse
import com.example.myapplication.data.remote.data.Resource
import com.example.myapplication.data.repository.user.IUserControllerRepository
import com.colan.kindercare.ui.base.BaseNavigator
import com.colan.kindercare.ui.base.BaseViewModel
import com.example.myapplication.data.remote.data.response.UserProfileResponse
import com.example.myapplication.utils.SingleLiveData
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivityVM(application: Application) : BaseViewModel<BaseNavigator>(application),KoinComponent {

    val iSharedPreferenceService: ISharedPreferenceService by inject()
    val dashboardControllerRepository: IUserControllerRepository by inject()
    var getUserProfileJob: Job? = null

    val mShowProgressBar = SingleLiveData<Boolean>()

    val getUserProfileResponse = MutableLiveData<Resource<BaseResponse<ArrayList<UserProfileResponse>>>>()

    init {
        getUserProfile()
    }


    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }



    fun getUserProfile(){
        mShowProgressBar.value = true
        GlobalScope.launch{
            getUserProfileJob=async(Dispatchers.IO) {
                dashboardControllerRepository.getUserProfile(getUserProfileResponse)
            }
        }

    }

}