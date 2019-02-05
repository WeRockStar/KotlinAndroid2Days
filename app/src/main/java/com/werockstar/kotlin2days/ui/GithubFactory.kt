package com.werockstar.kotlin2days.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.werockstar.kotlin2days.api.GithubAPI
import com.werockstar.kotlin2days.scheduler.IScheduler

class GithubFactory(private val api: GithubAPI, private val scheduler: IScheduler) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GithubViewModel::class.java)) {
            GithubViewModel(api, scheduler) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}