package com.view.mvvmdemo.core.network

import com.view.mvvmdemo.bean.addUserBean
import com.view.mvvmdemo.bean.versionBean
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface IPostApiClient {

    @GET("/getVersion")
    suspend fun getVersion(): versionBean

    @Headers("Content-Type: application/json")
    @POST("/addUser")
    suspend fun addUser(@Body requestBody: RequestBody): addUserBean


}