package com.example.weather_android.models.hourly_api_models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geoloc(

    @Json(name = "lng")
    val lng: Double? = null,

    @Json(name = "lat")
    val lat: Double? = null
)
