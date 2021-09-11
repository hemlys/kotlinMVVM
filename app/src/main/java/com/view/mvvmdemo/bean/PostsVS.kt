package com.view.mvvmdemo.bean

sealed class PostsVS {
    class Error(val message: String?) : PostsVS()
    class ShowLoader(val showLoader: Boolean) : PostsVS()

    class Version(val version: versionBean) : PostsVS()
    class addUser(val adduser: addUserBean) : PostsVS()
}