package com.werockstar.kotlin2days

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("users/{user}")
    fun user(@Path("user") userId: String): Observable<Void>

    @GET("login")
    fun login(username: String, password: String): Observable<Void>
}
