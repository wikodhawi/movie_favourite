package com.stickearn.moviefavourite.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stickearn.moviefavourite.R
import com.stickearn.moviefavourite.base.BaseViewModel
import com.stickearn.moviefavourite.model.entity.DummyEntity
import com.stickearn.moviefavourite.model.login.LoginUser
import com.stickearn.moviefavourite.repository.login.LoginRepository
import com.stickearn.moviefavourite.service.database.dao.DummyEntityDao
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class LoginViewModel (private val loginRepository: LoginRepository,
            val dummyEntityDao: DummyEntityDao): BaseViewModel() {
    var email = ""
    var password = ""

    val workerLogin = MutableLiveData<LoginUser>()
    val errorLogin = MutableLiveData<Int>()

    fun testDb(){
        viewModelScope.launch {
            dummyEntityDao.insert(DummyEntity(1, "test"))
        }
    }

    fun loginWorker(){
        isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = loginRepository.loginApi(email, password)
                if(response.errorMessage.isNullOrBlank())
                {
                    workerLogin.postValue(response)
                }
                else
                {
                    errorLogin.postValue(R.string.srFailedToLoginPleaseCheckYourUsernameAndPassword)
                }
                isLoading.postValue(false)
            }
            catch (e: Exception)
            {
                when(e) {
                    is UnknownHostException -> {
                        errorLogin.postValue(R.string.srSomethingWentWrongPleaseTryAgainLater)
                    }
                    is HttpException -> {
                        errorLogin.postValue(R.string.srServerError)
                    }
                    else -> {
                        errorLogin.postValue(R.string.srServerError)
                    }
                }
                isLoading.postValue(false)
            }
        }
    }

}