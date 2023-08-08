package com.example.weather_android.models.daily_api_models
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.format.TextStyle
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@JsonClass(generateAdapter = true)
class Daily (
    @Json(name = "dt"        ) var dt        : Long?              = null,
    @Json(name = "sunrise"   ) var sunrise   : Int?               = null,
    @Json(name = "sunset"    ) var sunset    : Int?               = null,
    @Json(name = "moonrise"  ) var moonrise  : Int?               = null,
    @Json(name = "moonset"   ) var moonset   : Int?               = null,
    @Json(name = "moon_phase") var moonPhase : Double?            = null,
    @Json(name = "temp"      ) var temp      : Temp?              = Temp(),
    @Json(name = "feels_like") var feelsLike : FeelsLike?         = FeelsLike(),
    @Json(name = "pressure"  ) var pressure  : Int?               = null,
    @Json(name = "humidity"  ) var humidity  : Int?               = null,
    @Json(name = "dew_point" ) var dewPoint  : Double?            = null,
    @Json(name = "wind_speed") var windSpeed : Double?            = null,
    @Json(name = "wind_deg"  )  var windDeg   : Int?              = null,
    @Json(name = "wind_gust" ) var windGust  : Double?            = null,
    @Json(name = "weather"   ) var weather   : ArrayList<Weather> = arrayListOf(),
    @Json(name = "clouds"    ) var clouds    : Int?               = null,
    @Json(name = "pop"       ) var pop       : Double?            = null,
    @Json(name = "rain"      ) var rain      : Double?            = null,
    @Json(name = "uvi"       ) var uvi       : Double?            = null

){
    fun getWeatherItem(): Weather? {
        return weather?.first()
    }

    fun getDay(): String? {
        return dt?.let { getDateTime(it)?.getDisplayName(TextStyle.FULL, Locale.getDefault()) }
    }

    private fun getDateTime(s: Long): DayOfWeek? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s * 1000)
            val formattedDate = sdf.format(netDate)

            LocalDate.of(
                formattedDate.substringAfterLast("/").toInt(),
                formattedDate.substringAfter("/").take(2).toInt(),
                formattedDate.substringBefore("/").toInt()
            )
                .dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }

    fun getDate(): String? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(dt!!.toLong() * 1000)
            sdf.format(netDate)

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getDateTime(): String? {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.GERMAN)
            val netDate = Date(dt!!.toLong() * 1000)
            sdf.format(netDate)


        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
