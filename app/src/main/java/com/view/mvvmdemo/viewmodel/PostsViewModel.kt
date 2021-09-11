package com.view.mvvmdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.view.mvvmdemo.Interactor.PostsInteractor
import com.view.mvvmdemo.bean.PostsVS
import com.view.mvvmdemo.core.mapper.adduserVMMapper
import com.view.mvvmdemo.core.platform.BaseViewModel
import com.view.mvvmdemo.core.utils.io
import com.view.mvvmdemo.core.utils.ui
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class PostsViewModel(
    private val postsInteractor: PostsInteractor
) : BaseViewModel() {

    val viewState: LiveData<PostsVS> get() = mViewState
    private val mViewState = MutableLiveData<PostsVS>()
    private val madduserVMMapper by lazy { adduserVMMapper() }


    fun addUser(requestBody: RequestBody) {
        viewModelScope.launch {
            mViewState.value = PostsVS.ShowLoader(true)
            try {
                io {
                    postsInteractor.execute(requestBody)
                        .collect {
                            ui { mViewState.value = PostsVS.addUser(madduserVMMapper.map(it)) }
                        }
                }
            } catch (e: Exception) {
                ui { mViewState.value = PostsVS.Error(e.message) }
            }
            mViewState.value = PostsVS.ShowLoader(false)
        }
    }


}