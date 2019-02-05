package com.werockstar.kotlin2days.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.werockstar.kotlin2days.BuildConfig
import com.werockstar.kotlin2days.R
import com.werockstar.kotlin2days.api.HttpManager
import com.werockstar.kotlin2days.api.HttpModule
import com.werockstar.kotlin2days.scheduler.AppScheduler
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity(), GithubView {

    private lateinit var presenter: GithubPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = HttpModule().createAPI()
        presenter = GithubPresenter(api, AppScheduler())
        presenter.attachView(this)

        btnRequest.setOnClickListener { presenter.getUser("werockstar") }

        doOnDebug({
            Log.d("SQL", "SELECT *FROM USER_PASSWORD")
        }, 5)
    }

    private fun doOnDebug(func: () -> Unit, number: Int) {
        println("Start")

        if (BuildConfig.DEBUG) {
            func()
        }

        println("End")
    }

    override fun onUserResult(result: GithubResponse) {
        tvName?.text = result.user
    }

    data class Person(val name: String, val age: Int)

    override fun showLoading() {
        val users = listOf(
            Person("WeRockStar", 25),
            Person("Aou", 20),
            Person("Title", 22)
        )

        val list = users.filter { it.age >= 20 }
            .filter { it.name.length > 3 }
            .map { Person("Mr.${it.name}", it.age) }
            .map { it.name }


        println(list.toString())
    }

    override fun onUserError(message: String?) {

    }

    override fun dismissLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onDestroy()

        val sum = 1 บวก 2
    }

    infix fun Int.บวก(b: Int): Int {
        return this + b
    }
}
