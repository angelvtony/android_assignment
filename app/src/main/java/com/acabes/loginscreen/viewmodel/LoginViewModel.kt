package com.acabes.loginscreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acabes.loginscreen.models.CreditialData
import com.acabes.loginscreen.models.LoginResponse
import com.acabes.loginscreen.network.ApiInterface
import com.acabes.loginscreen.network.RetrofitHelper
import kotlinx.coroutines.launch

class LoginViewModel :ViewModel() {
    var login:MutableLiveData<LoginResponse> = MutableLiveData()

    fun fetchLogin(username:String, password : String){
        viewModelScope.launch {
            try {
                val retrofit = RetrofitHelper.getInstance()
                val service = retrofit.create(ApiInterface::class.java)

                val responses = service.postData(CreditialData(username, password))
                login.value= responses
            }
            catch (e:Exception){

            }
        }
    }
}