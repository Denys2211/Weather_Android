package com.example.weather_android.models.daily_api_models
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class FeelsLike (
    @Json(name = "day"  ) var day   : Double? = null,
    @Json(name = "night") var night : Double? = null,
    @Json(name = "eve"  ) var eve   : Int?    = null,
    @Json(name = "morn" ) var morn  : Double? = null

)
