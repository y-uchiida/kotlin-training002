package com.example.kotlin_training002

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HighAndLowGameClearActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_and_low_game_clear)

        // ゲーム画面から取得したデータを受け取る
        val answerNumber = intent.getIntExtra("answerNumber", 1)
        val answerCount = intent.getIntExtra("answerCount", 1)
        // テキストを描画
        val textHighAndLowGameResultText: TextView = findViewById(R.id.textHighAndLowGameResultText)
        textHighAndLowGameResultText.text = "正解は「${answerNumber}」でした！🎉\n${answerCount}回の回答で正解しました。"

        // ボタンクリック時の処理
        // ゲーム画面に戻る
        val btnHighAndLowGameRestart: Button = findViewById(R.id.btnHighAndLowGameRestart)
        btnHighAndLowGameRestart.setOnClickListener {
            val intent = Intent(this, HighAndLowGameActivity::class.java)
            startActivity(intent)
        }
    }
}