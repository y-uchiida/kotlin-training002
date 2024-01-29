package com.example.kotlin_training002

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlin.random.Random

class HighAndLowGameActivity : BaseActivity() {
    private var answerNumber = 0;
    private var answerCount = 0;

    private val generateAnswerCountText = fun (count: Int): String {
        return "${count} 回目の回答です！"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_and_low_game)

        // toolbarを表示
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // 初期化
        answerNumber = Random.nextInt(100) + 1
        answerCount = 1

        // 初期表示テキストを設定
        val textHint: TextView = findViewById(R.id.textHint)
        textHint.text = "1から100までの数字を当ててください！"
        val answerCountText = findViewById<TextView>(R.id.answerCountText)
        answerCountText.text = generateAnswerCountText(answerCount)

    }
}