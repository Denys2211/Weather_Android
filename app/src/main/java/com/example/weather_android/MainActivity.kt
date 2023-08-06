package com.example.weather_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather_android.databinding.GeneralPageBinding
import com.example.weather_android.ui.theme.Weather_AndroidTheme

class MainActivity : ComponentActivity() {
    private lateinit var baseView: GeneralPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseView = GeneralPageBinding.inflate(layoutInflater)
        setContentView(baseView.root)
    }
}
