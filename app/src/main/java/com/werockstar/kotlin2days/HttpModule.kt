package com.werockstar.kotlin2days

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpModule {

    private fun createOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(createOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun createAPI(): GithubAPI {
        val retrofit: Retrofit = createRetrofit()
        val api: GithubAPI = retrofit.create(GithubAPI::class.java)
        return api
    }

}