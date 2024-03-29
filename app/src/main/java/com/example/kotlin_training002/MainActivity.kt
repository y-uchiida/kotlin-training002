package com.example.kotlin_training002

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // toolbarを表示する
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // カレンダーアクティビティに遷移するためのボタン要素を取得
        val btnOpenMyCalendarActivity: Button = findViewById(R.id.btnOpenMyCalendar)
        // クリックイベントのリスナーを設定
        btnOpenMyCalendarActivity.setOnClickListener{
            val intent = Intent(this, MyCalendarActivity::class.java)
            startActivity(intent)
        }

        // MessageDisplayActivity に遷移するためのボタン要素を取得
        val btnOpenMessageDisplay: Button = findViewById(R.id.btnOpenMessageDisplay)
        // クリックイベントのリスナーを設定
        btnOpenMessageDisplay.setOnClickListener {
            val intent = Intent(this, MessageDisplayActivity::class.java)

            // 遷移先のアクティビティに値を渡す
            val myMessage: EditText = findViewById(R.id.myMessageString)
            val message = myMessage.text.toString()
            // putExtra() で、遷移先のアクティビティ(intent) に値を渡す
            intent.putExtra("message", message)
            startActivity(intent)
        }
    }
}
