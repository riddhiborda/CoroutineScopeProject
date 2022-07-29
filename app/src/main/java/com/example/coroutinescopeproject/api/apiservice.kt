package com.example.coroutinescopeproject.api

import com.example.coroutinescopeproject.response.UserListResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface apiservice {
    @GET("api/users?page=2")
    suspend fun listUSer(
    ): @JvmSuppressWildcards Response<UserListResponse>

}