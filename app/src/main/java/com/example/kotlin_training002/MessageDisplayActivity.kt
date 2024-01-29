package com.example.kotlin_training002

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MessageDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_display)

        // 送信されたメッセージを取得
        val message = intent.getStringExtra("message")

        // メッセージを表示するためのText ビューを取得
        val textView: TextView = findViewById(R.id.sentMessageFromMain)
        // 表示するテキストを、受け取ったメッセージで代入
        textView.text = message

        val btn: Button = findViewById(R.id.btnOpenMainOnMessageDisplay)

        btn.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}