package com.view.mvvmdemo.di

import com.view.mvvmdemo.Interactor.PostsInteractor
import com.view.mvvmdemo.Interactor.VersionInteractor
import com.view.mvvmdemo.core.network.PostRestDataStore
import com.view.mvvmdemo.core.repository.PostRepository
import com.view.mvvmdemo.core.repository.PostRepositoryImpl
import com.view.mvvmdemo.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val postModule = module {

    //region ViewModel
    viewModel {
        PostsViewModel(get())
    }
    viewModel {
        VersionModel(get())
    }
    //endregion

    //region Interactor
    single {
        PostsInteractor(
            get()
        )
    }
    single {
        VersionInteractor(
            get()
        )
    }

    //endregion

    //region Repository
    single<PostRepository> {
        PostRepositoryImpl(get())
    }
    //endregion

    //region Datastore
    single {
        PostRestDataStore()
    }


    //endregion
}

val modules = listOf(postModule)