package com.werockstar.kotlin2days

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class HttpModule {

    private fun createOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(createOkHttp())
            .build()
    }

    fun createAPI(): GithubAPI {
        val retrofit = createRetrofit()
        return retrofit.create(GithubAPI::class.java)
    }


}