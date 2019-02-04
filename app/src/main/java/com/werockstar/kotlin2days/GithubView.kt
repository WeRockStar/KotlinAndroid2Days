package com.werockstar.kotlin2days

interface GithubView {
    fun showLoading()
    fun onUserResult(result: GithubResponse)
    fun onUserError(message: String?)
    fun dismissLoading()
}