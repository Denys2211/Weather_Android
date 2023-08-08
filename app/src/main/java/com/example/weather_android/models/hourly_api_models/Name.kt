package com.example.weather_android.models.hourly_api_models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(

    @Json(name = "matchLevel")
    val matchLevel: String? = null,

    @Json(name = "fullyHighlighted")
    val fullyHighlighted: Boolean? = null,

    @Json(name = "value")
    val value: String? = null,

    @Json(name = "matchedWords")
    val matchedWords: List<String?>? = null
)