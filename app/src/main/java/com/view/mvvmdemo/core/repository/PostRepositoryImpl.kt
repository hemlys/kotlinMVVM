package com.view.mvvmdemo.core.repository

import com.view.mvvmdemo.bean.addUserBean
import com.view.mvvmdemo.bean.versionBean
import com.view.mvvmdemo.core.mapper.adduserVMMapper
import com.view.mvvmdemo.core.mapper.versionVMMapper
import com.view.mvvmdemo.core.network.PostRestDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.RequestBody

class PostRepositoryImpl(
    private val postRestDataStore: PostRestDataStore
) : PostRepository {


    private val mversionVMMapper by lazy { versionVMMapper() }

    private val madduserVMMapper by lazy { adduserVMMapper() }




    override fun getVersion(): Flow<versionBean> =
        postRestDataStore.getVersion().map {
            mversionVMMapper.map(it)
        }

    override fun addUser(requestBody: RequestBody): Flow<addUserBean> =
        postRestDataStore.addUser(requestBody).map {
            madduserVMMapper.map(it)
        }


}
