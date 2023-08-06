package com.example.weather_android

import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import com.example.weather_android.databinding.GeneralPageBinding


class MainActivity : ComponentActivity() {
    private lateinit var baseView: GeneralPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseView = GeneralPageBinding.inflate(layoutInflater)
        setContentView(baseView.root)

        setHeightFrame()
    }

    private fun setHeightFrame(){
        val height = Resources.getSystem().displayMetrics.heightPixels
        baseView.frameListDailyWeather.layoutParams.height = height - 300
    }
}

