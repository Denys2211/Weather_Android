package com.example.weather_android.models.daily_api_models
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Minutely (
    @Json(name = "dt"           ) var dt            : Int? = null,
    @Json(name = "precipitation") var precipitation : Double? = null
)