package com.example.weather_android

import android.R
import android.content.res.Resources
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_android.controls.ScrollViewWithEndFunc
import com.example.weather_android.core.Constants
import com.example.weather_android.databinding.GeneralPageBinding
import com.example.weather_android.models.daily_api_models.Daily
import com.example.weather_android.models.daily_api_models.ForecastResponse
import com.example.weather_android.services.RetrofitServiceBuilder
import com.example.weather_android.services.RetrofitServiceInterface
import com.example.weather_android.viewModels.ForecastAdapter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale


class MainActivity : ComponentActivity() {
    private lateinit var mGeocoder: Geocoder
    private var heightRoot: Int = 1
    private lateinit var baseView: GeneralPageBinding
    private lateinit var listDaily: List<Daily>
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        baseView = GeneralPageBinding.inflate(layoutInflater)
        setContentView(baseView.root)
        mGeocoder = Geocoder(applicationContext, Locale.getDefault())
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

                baseView.weatherOnDay.translationY = -scrollView!!.scrollY.toFloat()
                baseView.imageMenu.translationY = scrollView!!.scrollY.toFloat()
                baseView.weatherOnDay.alpha = 1 - (alpha * 5)
            }
            override fun onEndScroll(scrollView: ScrollViewWithEndFunc?) {

            }
        })
    }

    fun getForecast() {
        var retrofit = RetrofitServiceBuilder.buildService(RetrofitServiceInterface::class.java)
        retrofit.getForecast(
            49.8, 24.0,
            Constants.NetworkService.API_KEY_VALUE,
            Constants.Coords.METRIC
        ).enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                try {

                    var responseBody = response.body()
                    listDaily = responseBody!!.daily!!

                    var adapter = ForecastAdapter(listDaily)
                    baseView.forecastRecycle.adapter = adapter

                    setCurrentWeather(responseBody)

                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {

            }
        })

    }

    fun setCurrentWeather(forecast: ForecastResponse?){
        baseView.textViewCelsius.setText(forecast?.current?.temp!!.toInt().toString())
        var city = mGeocoder.getFromLocation(forecast.lat!!, forecast.lon!!, 1)
        baseView.textCityName.setText(if(city?.first()?.locality == null) city?.first()?.adminArea else city?.first()?.locality)
        baseView.textCloudity.setText(forecast!!.daily[0].weather[0].description)
        baseView.textWind.setText("${forecast!!.daily[0].windGust.toString()}m/s")
        baseView.textHumidity.setText("${forecast!!.current?.humidity.toString()}%")
        baseView.textPressure.setText("${forecast!!.current?.pressure.toString()}hpa")
        baseView.textCloudiness.setText("${forecast!!.current?.clouds.toString()}%")

        val resID: Int = resources.getIdentifier("a${forecast!!.current!!.weather[0].icon}_svg", "drawable", packageName)
        baseView.imageViewWeather.setImageResource(resID)
        baseView.DateTime.setText(forecast!!.current?.getDateTime())
    }


}

