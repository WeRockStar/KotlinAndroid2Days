package com.werockstar.kotlin2days

class GithubPresenter constructor(private val api: GithubAPI, private val scheduler: IScheduler) {

    private lateinit var view: GithubView

    fun attachView(view: GithubView) {
        this.view = view
    }

    fun getUser(userId: String) {
        view.showLoading()
        api.user(userId)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .doOnTerminate { view.dismissLoading() }
            .subscribe({
                view.onUserResult(it)
            }, {
                view.onUserError(it.message)
            })
    }
}