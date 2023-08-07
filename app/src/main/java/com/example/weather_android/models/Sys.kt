package com.example.weather_android.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Sys(

    @Json(name = "pod")
    val pod: String?
) : Parcelable
