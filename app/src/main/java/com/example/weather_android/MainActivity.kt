package com.example.weather_android

import android.R
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.example.weather_android.controls.ScrollViewWithEndFunc
import com.example.weather_android.core.Constants
import com.example.weather_android.databinding.GeneralPageBinding
import com.example.weather_android.models.ForecastResponse
import com.example.weather_android.models.ListItem
import com.example.weather_android.services.RetrofitServiceBuilder
import com.example.weather_android.services.RetrofitServiceInterface
import com.jakewharton.threetenabp.AndroidThreeTen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {
    private var heightRoot: Int = 1
    private lateinit var baseView: GeneralPageBinding
    private lateinit var listDaily: List<ListItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this);

        baseView = GeneralPageBinding.inflate(layoutInflater)
        setContentView(baseView.root)

        setHeightFrame()

        setScrollListener()

        getForecast()
    }

    fun getColorWithAlpha(alpha: Float, baseColor: Int): Int {
        val a = 255.coerceAtMost(0.coerceAtLeast((alpha * 255).toInt())) shl 24
        val rgb = R.color.holo_purple and baseColor
        return a + rgb
    }
    private fun setHeightFrame(){
        heightRoot = Resources.getSystem().displayMetrics.heightPixels
        baseView.frameListDailyWeather.layoutParams.height = heightRoot - 300
    }

    fun setScrollListener(){
        baseView.scrollView2.setOnScrollListener(object : ScrollViewWithEndFunc.OnScrollListener {
            override fun onScrollChanged(scrollView: ScrollViewWithEndFunc?, x: Int, y: Int, oldX: Int, oldY: Int) {
                val alpha: Float = scrollView!!.scrollY.toFloat() / heightRoot
                baseView.rootGrid.setBackgroundColor(
                    getColorWithAlpha(
                        alpha,
                        ContextCompat.getColor(baseContext, R.color.background_dark)
                    )
                )

                baseView.weatherOnDay.translationY = -scrollView!!.scrollY.toFloat() + 100
                baseView.weatherOnDay.alpha = 1 - (alpha * 5)
            }
            override fun onEndScroll(scrollView: ScrollViewWithEndFunc?) {

            }
        })
    }

    fun getForecast(){
        var retrofit = RetrofitServiceBuilder.buildService(RetrofitServiceInterface::class.java)
        try{
            retrofit.getForecast(49.8,24.0,
                Constants.NetworkService.API_KEY_VALUE,
                Constants.Coords.METRIC).enqueue(object: Callback<ForecastResponse>{
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    try{
                        var responseBody = response.body()
                        listDaily = responseBody!!.list!!

                    }catch (ex: Exception){
                        ex.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    var d = t
                }

            })

        }catch (ex1: Exception){
            ex1.printStackTrace()
        }

    }
}

