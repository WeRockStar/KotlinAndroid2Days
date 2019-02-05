package com.werockstar.kotlin2days.ui

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class GithubResponse(@SerializedName("login") val user: String) : Parcelable