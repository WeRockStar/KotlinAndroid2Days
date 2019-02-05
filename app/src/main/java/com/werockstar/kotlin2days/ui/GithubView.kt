package com.werockstar.kotlin2days.ui

interface GithubView {
    fun showLoading()
    fun onUserResult(result: GithubResponse)
    fun onUserError(message: String?)
    fun onUnAuthorize()
    fun dismissLoading()
}