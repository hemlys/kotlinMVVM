package com.view.mvvmdemo.Interactor

import com.view.mvvmdemo.bean.versionBean
import com.view.mvvmdemo.core.interactor.Interactor
import com.view.mvvmdemo.core.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class VersionInteractor(
    private val postRepository: PostRepository
) : Interactor<Interactor.None, Flow<versionBean>> {


    override fun execute(params: Interactor.None): Flow<versionBean> {
        return postRepository.getVersion()
    }


}