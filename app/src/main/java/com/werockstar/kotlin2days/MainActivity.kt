package com.werockstar.kotlin2days

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: GithubPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = HttpModule().createAPI()
        presenter = GithubPresenter(api)

        btnRequest.setOnClickListener {
            presenter.getUser("werockstar")
        }
    }
}
