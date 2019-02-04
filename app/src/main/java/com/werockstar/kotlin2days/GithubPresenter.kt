package com.werockstar.kotlin2days

import android.util.Log
import android.widget.Toast
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GithubPresenter constructor(private val api: GithubAPI) {

    private lateinit var view: GithubView

    fun attachView(view: GithubView) {
        this.view = view
    }

    fun getUser(userId: String) {
        api.user(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                // TODO: onNext

            }, {
                // TODO: onError

            }, {
                // TODO: onComplete

            })
    }
}