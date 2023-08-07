package com.example.weather_android.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Snow(

    @Json(name = "3h")
    val jsonMember3h: Double?
) : Parcelable