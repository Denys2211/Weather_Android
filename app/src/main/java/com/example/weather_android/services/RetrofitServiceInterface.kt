package com.example.weather_android.services

import com.example.weather_android.models.hourly_api_models.CurrentWeatherResponse
import com.example.weather_android.models.daily_api_models.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceInterface {

    @GET("onecall")
    fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String
    ):Call<ForecastResponse>

    @GET("weather")
    fun getCurrentByGeoCords(
        @Query("lat")
        lat: Double,
        @Query("lon")
        lon: Double,
        @Query("appid") appid: String,
        @Query("units")
        units: String
    ): Call<CurrentWeatherResponse>
}