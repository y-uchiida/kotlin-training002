package com.example.kotlin_training002

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ボタン要素を取得
        val btnOpenMyCalendarActivity: Button = findViewById(R.id.btnOpenMyCalendar)

        // クリックイベントのリスナーを設定
        btnOpenMyCalendarActivity.setOnClickListener{
            val intent = Intent(this, MyCalendarActivity::class.java)
            startActivity(intent)
        }
    }
}