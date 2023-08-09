package com.example.weather_android.models.daily_api_models
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
class Weather (

    @Json(name = "id"         ) var id          : Int?    = null,
    @Json(name = "main"       ) var main        : String? = null,
    @Json(name = "description") var description : String? = null,
    @Json(name = "icon"       ) var icon        : String? = null
)