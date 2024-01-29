package com.example.kotlin_training002

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import java.text.SimpleDateFormat
import java.util.*

class MyCalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_calendar)

        // MainActivity に遷移するボタン要素を取得
        val btnOpenMain: Button = findViewById(R.id.btnOpenMain)
        // クリックイベントのリスナーを設定
        btnOpenMain.setOnClickListener {
            // intent で表示するActivity を指定
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 選択中の日付をダイアログに表示するボタンの要素を取得
        val btnShowSelectedDate: Button = findViewById(R.id.btnShowSelectedDate)
        // クリックイベントのリスナーを設定
        btnShowSelectedDate.setOnClickListener {
            // カレンダーのビューを取得
            val calendarView: CalendarView = findViewById(R.id.calendarView)

            // カレンダーで選択されている日付を取得し、テキストにフォーマットする
            val selectedDate = Date(calendarView.date)
            val formattedDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(selectedDate)

            // ダイアログを開いて、選択されている日付を表示
            AlertDialog.Builder(this)
                .setTitle("Selected Date") // ダイアログのタイトルを設定
                .setMessage(formattedDate) // ダイアログのメッセージを設定
                .setPositiveButton("OK", DialogInterface.OnClickListener {
                    // ダイアログのOKボタンを押したらログを出力する
                    dialog, witch -> Log.d("MyCalendarActivity", "click positive button on dialog")
                })
                .show()
        }
    }
}
