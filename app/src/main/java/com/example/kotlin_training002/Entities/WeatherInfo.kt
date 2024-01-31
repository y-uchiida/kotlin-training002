package com.example.kotlin_training002.Entities

// 気象情報のデータを持たせるエンティティ
// 気象情報APIのエンドポイントから受け取ったJSONから一部を受け取る
data class WeatherInfo (
    val publishingOffice: String? = null,
    val reportDatetime: String? = null,
    val targetArea: String? = null,
    val headlineText: String? = null,
    val text: String? = null
)