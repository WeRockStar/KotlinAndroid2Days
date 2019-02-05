package com.werockstar.kotlin2days.ui

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.werockstar.kotlin2days.R
import com.werockstar.kotlin2days.TestApp
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java, true, false)
    private val mockWebServer = MockWebServer()

    @Test
    fun click_request_button_should_see_WeRockStar() {
        mockWebServer.start()
        val app = InstrumentationRegistry.getTargetContext().applicationContext as TestApp
        app.applyBaseUrl(mockWebServer.url("/").toString())
        mockWebServer.enqueue(
            MockResponse().setBody(
                """
            {
                "login": "WeRockStar"
            }
        """.trimIndent()
            )
        )
        rule.launchActivity(null)

        onView(withId(R.id.btnRequest))
            .perform(ViewActions.click())

        onView(withId(R.id.tvName))
            .check(ViewAssertions.matches(withText("WeRockStar")))

        mockWebServer.shutdown()
    }
}