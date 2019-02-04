package com.werockstar.kotlin2days

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("users/{user}")
    fun user(@Path("user") userId: String): Call<Void>
}
