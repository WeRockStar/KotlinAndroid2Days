package com.werockstar.kotlin2days.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.werockstar.kotlin2days.api.GithubAPI
import com.werockstar.kotlin2days.api.Resource
import com.werockstar.kotlin2days.scheduler.IScheduler
import io.reactivex.disposables.CompositeDisposable

class GithubViewModel(private val api: GithubAPI, private val scheduler: IScheduler) : ViewModel() {

    val liveData = MutableLiveData<Resource<GithubResponse>>()
    private val disposable by lazy { CompositeDisposable() }

    fun getUser(userId: String) {
        liveData.value = Resource.loading(null)
        disposable.add(
            api.user(userId)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    liveData.value = Resource.success(it)
                }, {
                    liveData.value = Resource.error(it?.message ?: "", null)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }
}