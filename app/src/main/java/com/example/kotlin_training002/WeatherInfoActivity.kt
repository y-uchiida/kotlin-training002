package com.example.kotlin_training002

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.kotlin_training002.Entities.WeatherInfo
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject

class WeatherInfoActivity : BaseActivity() {

    private fun addMessage(message: String) {
        val container = findViewById<LinearLayout>(R.id.WeatherInfoContainer)
        val textView = TextView(this)
        textView.text = message
        textView.textSize = 16F
        container.addView(textView)
    }

    private fun formatWeatherInfo(json: JSONObject): WeatherInfo {
        // 取得したJSONとクラスのプロパティを関連づけ(マッピング)するための機能を備えた
        // ObjectMapper インスタンスを生成
        val mapper = ObjectMapper()

        // ObjectMapper の機能を用いて、JSONの中からWeatherInfoクラスのプロパティ名と一致する値を当てはめる
        return mapper.readValue(json.toString(), WeatherInfo::class.java)
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
                // 通信成功時の処理:
                // 取得したデータをWeatherInfo インスタンスに変換し、
                // そのtextプロパティをaddMessage() メソッドを通じて画面に表示
                // weatherInfo のtext プロパティは nullable なので、
                // エルビス演算子を用いてnullチェックを行い、null の場合は "no data" を表示する
                response ->
                    val weatherInfo = formatWeatherInfo(response)
                    addMessage(weatherInfo.text ?: "no data")
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
