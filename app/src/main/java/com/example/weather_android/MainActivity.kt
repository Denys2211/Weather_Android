package com.example.weather_android

import android.R
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.example.weather_android.controls.DirectionScroll
import com.example.weather_android.controls.ScrollViewWithEndFunc
import com.example.weather_android.databinding.GeneralPageBinding


class MainActivity : ComponentActivity() {
    private var heightRoot: Int = 1
    private lateinit var baseView: GeneralPageBinding
    private var minimum: Float = 0.0f
    private var maximum: Float = 10.0f
    private var from: Float = 600.0f
    private var to: Int = 0
    private var s: Float = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseView = GeneralPageBinding.inflate(layoutInflater)
        setContentView(baseView.root)

        setHeightFrame()

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

            }})
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
}

