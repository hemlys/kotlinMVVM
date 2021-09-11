package com.view.mvvmdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.view.mvvmdemo.Interactor.VersionInteractor
import com.view.mvvmdemo.bean.PostsVS
import com.view.mvvmdemo.core.mapper.versionVMMapper
import com.view.mvvmdemo.core.interactor.Interactor
import com.view.mvvmdemo.core.platform.BaseViewModel
import com.view.mvvmdemo.core.utils.io
import com.view.mvvmdemo.core.utils.ui
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class VersionModel(
    private val versionInteractor: VersionInteractor
) : BaseViewModel() {

    val viewState: LiveData<PostsVS> get() = mViewState
    private val mViewState = MutableLiveData<PostsVS>()
    private val mversionVMMapper by lazy { versionVMMapper() }


    fun getVersion() {
        viewModelScope.launch {
            mViewState.value = PostsVS.ShowLoader(true)
            try {
                io {
                    versionInteractor.execute(Interactor.None)
                        .collect {
                            ui { mViewState.value = PostsVS.Version(mversionVMMapper.map(it)) }
                        }
                }
            } catch (e: Exception) {
                ui { mViewState.value = PostsVS.Error(e.message) }
            }
            mViewState.value = PostsVS.ShowLoader(false)
        }
    }


}