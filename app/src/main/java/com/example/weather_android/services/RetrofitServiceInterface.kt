package com.example.weather_android.services

import com.example.weather_android.models.CurrentWeatherResponse
import com.example.weather_android.models.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceInterface {
    //@Headers("Content-Type:application/json")
    @GET("forecast")
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