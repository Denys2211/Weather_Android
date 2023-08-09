package com.example.weather_android.models.daily_api_models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ForecastResponse (
    @Json(name = "lat"            ) var lat            : Double?             = null,
    @Json(name = "lon"            ) var lon            : Double?             = null,
    @Json(name = "timezone"       ) var timezone       : String?             = null,
    @Json(name = "timezone_offset") var timezoneOffset : Int?                = null,
    @Json(name = "current"        ) var current        : Current?            = Current(),
    @Json(name = "minutely"       ) var minutely       : ArrayList<Minutely> = arrayListOf(),
    @Json(name = "hourly"         ) var hourly         : ArrayList<Hourly>   = arrayListOf(),
    @Json(name = "daily"          ) var daily          : ArrayList<Daily>    = arrayListOf()
)
