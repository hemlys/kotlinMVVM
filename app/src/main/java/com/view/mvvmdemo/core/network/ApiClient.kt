package com.view.mvvmdemo.core.network

import com.view.mvvmdemo.core.network.BaseApiClient
import com.view.mvvmdemo.core.network.IPostApiClient


object PostApiClient : BaseApiClient<IPostApiClient>(IPostApiClient::class.java)