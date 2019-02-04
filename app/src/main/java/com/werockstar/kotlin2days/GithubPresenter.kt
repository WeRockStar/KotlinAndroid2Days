package com.werockstar.kotlin2days

import com.werockstar.kotlin2days.api.GithubAPI
import com.werockstar.kotlin2days.scheduler.IScheduler
import io.reactivex.disposables.CompositeDisposable

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
                view.onUserError(it.message)
            })
        )
    }

    fun onDestroy() {
        disposable.clear()
    }
}