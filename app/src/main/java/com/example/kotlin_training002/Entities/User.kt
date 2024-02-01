package com.example.kotlin_training002.Entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

// APIから取得されるユーザー情報のエンティティ
// ユーザー詳細画面のViewに渡す必要があるので、Parcelize インターフェースを実装しておく
// 実装は @Parcelable アノテーションが済ませてくれる
@Parcelize
data class User (
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var registered_at: LocalDate? = null,
): Parcelable
