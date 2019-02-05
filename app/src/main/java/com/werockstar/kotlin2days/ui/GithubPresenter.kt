package com.werockstar.kotlin2days.ui

import com.werockstar.kotlin2days.api.GithubAPI
import com.werockstar.kotlin2days.scheduler.IScheduler
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

class GithubPresenter constructor(private val api: GithubAPI, private val scheduler: IScheduler) {

    private lateinit var view: GithubView

    private val disposable by lazy { CompositeDisposable() }

    fun attachView(view: GithubView) {
        this.view = view
    }

    fun getUser(userId: String) {
        view.showLoading()
        disposable.add(api.user(userId)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnTerminate { view.dismissLoading() }
            .subscribe({
                view.onUserResult(it)
            }, {
                if (it.message == "401") {
                    view.onUnAuthorize()
                } else {
                    view.onUserError(it.message)
                }
            })
        )
    }

    fun onDestroy() {
        disposable.clear()
    }
}