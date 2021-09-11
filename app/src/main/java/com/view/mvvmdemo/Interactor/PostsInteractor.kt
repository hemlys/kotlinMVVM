package com.view.mvvmdemo.Interactor

import com.view.mvvmdemo.bean.addUserBean
import com.view.mvvmdemo.core.interactor.Interactor
import com.view.mvvmdemo.core.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

class PostsInteractor(
    private val postRepository: PostRepository
) : Interactor<RequestBody, Flow<addUserBean>> {


    override fun execute(requestBody: RequestBody): Flow<addUserBean> {
        return postRepository.addUser(
            requestBody
        )
    }


}