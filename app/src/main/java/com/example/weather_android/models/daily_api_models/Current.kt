package com.example.weather_android.models.daily_api_models
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@JsonClass(generateAdapter = true)
class Current (

    @Json(name = "dt"           ) var dt         : Int?               = null,
    @Json(name = "sunrise"      ) var sunrise    : Int?               = null,
    @Json(name = "sunset"       ) var sunset     : Int?               = null,
    @Json(name = "temp"         ) var temp       : Double?            = null,
    @Json(name = "feels_like"   ) var feelsLike  : Double?            = null,
    @Json(name = "pressure"     ) var pressure   : Int?               = null,
    @Json(name = "humidity"     ) var humidity   : Int?               = null,
    @Json(name = "dew_point"    ) var dewPoint   : Double?            = null,
    @Json(name = "uvi"          ) var uvi        : Double?            = null,
    @Json(name = "clouds"       ) var clouds     : Int?               = null,
    @Json(name = "visibility"   ) var visibility : Int?               = null,
    @Json(name = "wind_speed"   ) var windSpeed  : Double?            = null,
    @Json(name = "wind_deg"     ) var windDeg    : Int?               = null,
    @Json(name = "wind_gust"    ) var windGust   : Double?            = null,
    @Json(name = "weather"      ) var weather    : ArrayList<Weather> = arrayListOf()
) {
    fun getDateTime(): String? {
        return try {
            val sdf = SimpleDateFormat("HH:mm dd-MM-yyyy ", Locale.getDefault())
            val netDate = Date(dt!!.toLong() * 1000)
            sdf.format(netDate)


        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

