package com.example.kotlin_training002

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class WeatherInfoActivity : BaseActivity() {

    private fun addMessage(message: String) {
        val container = findViewById<LinearLayout>(R.id.WeatherInfoContainer)
        val textView = TextView(this)
        textView.text = message
        textView.textSize = 16F
        container.addView(textView)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        // toolbarを表示する
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // APIエンドポイントより、気候情報を取得して画面に表示する
        val url = "https://www.jma.go.jp/bosai/forecast/data/overview_forecast/130000.json"
        // HTTPリクエスト情報をJsonObjectRequest インスタンスとして作成
        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                // 通信成功時の処理: 取得したデータをaddMessage() メソッドを通じて画面に表示
                json -> addMessage(json.toString())
            },
            {
                //通信失敗時の処理: エラーメッセージをaddMessage() メソッドを通じて画面に表示
                error ->  addMessage(error.toString())
            }
        )
        // 現在表示中のコンテクスト(Activity)に、作成したJsonObjectRequestをAjaxリクエストとして追加
        Volley.newRequestQueue(this).add(request)
    }
}
