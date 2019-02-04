package com.werockstar.kotlin2days.ui

import com.google.gson.annotations.SerializedName

class GithubResponse(@SerializedName("login") val user: String)