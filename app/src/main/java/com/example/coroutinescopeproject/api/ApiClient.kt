package com.example.coroutinescopeproject.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Reader
import java.util.concurrent.TimeUnit
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.Request

class ApiClient {
    companion object {

        const val baseUrl = "https://reqres.in/"
        fun instance(): apiservice = ApiClient.getClient().create(apiservice::class.java)
        //        const val baseUrl = "http://52.73.206.37/"
        private var retrofit: Retrofit? = null
        private val timeOut = 60L

        private var opt = OkHttpClient.Builder().apply {

//            val logging = HttpLoggingInterceptor()
//            logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }

            val httpClient = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            httpClient.readTimeout(5, TimeUnit.MINUTES)
            httpClient.writeTimeout(5, TimeUnit.MINUTES)
            httpClient.connectTimeout(5, TimeUnit.MINUTES)
            addInterceptor(logging)


        }.build()

        private fun getClient(): Retrofit {

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return if (retrofit != null) {
                retrofit!!
            } else {
                retrofit = Retrofit.Builder().apply {
                    client(opt)
                    baseUrl(baseUrl)
                    addConverterFactory(GsonConverterFactory.create(gson))
                }.build()
                retrofit!!
            }
        }

    }
}