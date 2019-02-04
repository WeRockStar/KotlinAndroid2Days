package com.werockstar.kotlin2days

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
    }

    override fun onUserResult(result: GithubResponse) {
        tvName.text = result.user
    }

    override fun showLoading() {

    }

    override fun onUserError(message: String?) {

    }

    override fun dismissLoading() {

    }
}
