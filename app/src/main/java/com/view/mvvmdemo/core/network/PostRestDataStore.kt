package com.view.mvvmdemo.core.network

import com.view.mvvmdemo.bean.addUserBean
import com.view.mvvmdemo.bean.versionBean
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody

class PostRestDataStore {


    fun addUser(requestBody: RequestBody): Flow<addUserBean> = flow {

        emit(PostApiClient.getApiClient().addUser(requestBody))
    }


    fun getVersion(): Flow<versionBean> = flow {
        emit(PostApiClient.getApiClient().getVersion())

    }


}