package com.acabes.loginscreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acabes.loginscreen.network.QuotesApi
import com.acabes.loginscreen.network.RetrofitHelperQuotes
import kotlinx.coroutines.launch


class QuotesViewModel : ViewModel() {

    var quotes: MutableLiveData<List<String>> = MutableLiveData()

    fun fetchQuotes() {

        viewModelScope.launch {
            try {
                val retrofit = RetrofitHelperQuotes.getInstance()
                val service = retrofit.create(QuotesApi::class.java)

                val response = service.getQuotes()

                quotes.value = response

            } catch (e: Exception) {

            }
        }

    }

}
