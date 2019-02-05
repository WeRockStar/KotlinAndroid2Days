package com.werockstar.kotlin2days.api

import android.app.Application
import com.werockstar.kotlin2days.App
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpModule {

    fun createOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private fun createRetrofit(application: Application): Retrofit {
        val app = application as App
        return Retrofit.Builder()
            .baseUrl(app.getBaseUrl())
            .client(createOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun createAPI(application: Application): GithubAPI {
        val retrofit: Retrofit = createRetrofit(application)
        return retrofit.create(GithubAPI::class.java)
    }

}