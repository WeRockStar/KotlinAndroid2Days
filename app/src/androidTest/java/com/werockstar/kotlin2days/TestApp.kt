package com.werockstar.kotlin2days

class TestApp : App() {

    private lateinit var baseUrl: String

    override fun getBaseUrl(): String {
        return baseUrl
    }

    fun applyBaseUrl(baseUrl: String) {
        this.baseUrl = baseUrl
    }
}