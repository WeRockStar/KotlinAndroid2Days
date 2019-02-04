package com.werockstar.kotlin2days

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class HttpModule {

    fun createOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
    }

}