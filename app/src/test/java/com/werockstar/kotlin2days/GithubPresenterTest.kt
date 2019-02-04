package com.werockstar.kotlin2days

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GithubPresenterTest {

    private lateinit var presenter: GithubPresenter

    @Mock
    lateinit var api: GithubAPI
    @Mock
    lateinit var view: GithubView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val scheduler = TestScheduler()

        presenter = GithubPresenter(api, scheduler)
        presenter.attachView(view)
    }

    @Test
    fun `request user success should call onUserResult`() {
        val userId = "WeRockStar"
        val response = GithubResponse(userId)
        whenever(api.user(userId)).doReturn(Observable.just(response))

        presenter.getUser(userId)

        verify(view).showLoading()
        verify(view).onUserResult(response)
        verify(view).dismissLoading()
    }

    @Test
    fun `request user error should call onUserError`() {
        val userId = "WeRockStar"
        val throwable = Throwable("Error!!")
        val response = GithubResponse(userId)
        whenever(api.user(userId)).doReturn(Observable.error(throwable))

        presenter.getUser(userId)

        verify(view, never()).onUserResult(response)
        verify(view, times(1)).onUserError(throwable.message)
    }

    class TestScheduler : IScheduler {

        override fun io(): Scheduler {
            return Schedulers.trampoline()
        }

        override fun ui(): Scheduler {
            return Schedulers.trampoline()
        }

    }
}