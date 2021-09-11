package com.view.mvvmdemo.core.repository

import com.view.mvvmdemo.bean.addUserBean
import com.view.mvvmdemo.bean.versionBean
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface PostRepository {
    fun getVersion(): Flow<versionBean>
    fun addUser(requestBody: RequestBody): Flow<addUserBean>
}