package com.example.kotlin_training002

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlin.random.Random

class HighAndLowGameActivity : BaseActivity() {
    private var answerNumber = 0;
    private var answerCount = 0;

    private val generateAnswerCountText = fun (count: Int): String {
        return "${count} 回目の回答です！"
    }

    private val checkAnswer = fun (input: Int) {
        if (input < answerNumber) {
            // 小さすぎる
            answerCount++
            val textHint: TextView = findViewById(R.id.textHint)
            textHint.text = "${input} よりも大きい数字です"

            return
        } else if (input > answerNumber){
            // 大きすぎる
            answerCount++
            val textHint: TextView = findViewById(R.id.textHint)
            textHint.text = "${input} よりも小さい数字です"
            return
        } else {
            // 正解の場合、クリア画面に遷移する
            val intent = Intent(this, HighAndLowGameClearActivity::class.java)
            intent.putExtra("answerCount", answerCount)
            intent.putExtra("answerNumber", answerNumber)
            startActivity(intent)
        }
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

        // 回答ボタンがクリックされたときのイベントハンドラ
        val btnAnswer: Button = findViewById(R.id.btnAnswer)
        btnAnswer.setOnClickListener {
            // 回答欄に入力されている値を取得
            val answerNumberInput: EditText = findViewById(R.id.answerNumberInput)
            val answerNumber = answerNumberInput.text.toString().toInt()
            checkAnswer(answerNumber)
            answerCountText.text = generateAnswerCountText(answerCount)
        }
    }

}