package com.example.kotlin_training002

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MyCalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_calendar)

        // ボタン要素を取得
        val btnOpenMain: Button = findViewById(R.id.btnOpenMain)

        // クリックイベントのリスナーを設定
        btnOpenMain.setOnClickListener {
            // intent で表示するActivity を指定
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}