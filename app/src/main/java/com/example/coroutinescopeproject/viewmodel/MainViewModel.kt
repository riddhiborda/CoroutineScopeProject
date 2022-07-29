package com.example.mvvmkotlin.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coroutinescopeproject.api.ApiClient
import com.example.coroutinescopeproject.checkInternetConnection
import com.example.coroutinescopeproject.response.UserListResponse
import com.google.gson.Gson
import kotlinx.coroutines.*
import retrofit2.Response
import java.lang.Exception

/**
 * Created by Mr.PM.. on 31/03/21.
 */

class MainViewModel constructor(val context: Context) : ViewModel() {

     val response = MutableLiveData<UserListResponse>()
    val demoData = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }



    fun listUserApi() {
        if (checkInternetConnection(context)) {
            loading.value = true
            job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                try {
                    withContext(Dispatchers.Main) {
                        val res = ApiClient.instance().listUSer()
                        if (res.isSuccessful) {
                            Log.e("TAG", "loginStandard:success " + res.body()?.data)
                            loading.value = false
                            response.postValue(res.body())
//                            withContext(Dispatchers.Main) {
//                                context.toast(res.body()?.message.toString())
//                            }
                        } else {
                            onError("Something went wrong")
                        }
                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        onError("$e")
                        Log.e("TAG", "loginStandard:error exception " + e.message)
                        loading.value = false
                    }
                }
            }
        } else {
            onError("No internet")
            loading.value = false
        }
    }




}