package com.werockstar.kotlin2days.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.werockstar.kotlin2days.BuildConfig
import com.werockstar.kotlin2days.R
import com.werockstar.kotlin2days.api.HttpModule
import com.werockstar.kotlin2days.scheduler.AppScheduler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GithubView {

    private lateinit var presenter: GithubPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = HttpModule().createAPI()
        presenter = GithubPresenter(api, AppScheduler())
        presenter.attachView(this)

        btnRequest.setOnClickListener { presenter.getUser("werockstar") }

        doOnDebug {
            Log.d("SQL", "SELECT *FROM USER_PASSWORD")
        }
    }

    private fun doOnDebug(func: () -> Unit) {
        println("Start")

        if (BuildConfig.DEBUG) {
            func()
        }

        println("End")
    }

    override fun onUserResult(result: GithubResponse) {
        tvName?.text = result.user
    }

    override fun onUnAuthorize() {

    }


    override fun showLoading() {
    }

    override fun onUserError(message: String?) {

    }

    override fun dismissLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDestroy()
    }
}
