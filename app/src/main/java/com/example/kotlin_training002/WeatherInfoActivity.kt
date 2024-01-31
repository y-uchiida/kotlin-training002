package com.example.kotlin_training002

import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class WeatherInfoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        // toolbarを表示する
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
}
