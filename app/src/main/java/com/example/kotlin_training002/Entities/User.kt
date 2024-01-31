package com.example.kotlin_training002.Entities

import java.time.LocalDate

// APIから取得されるユーザー情報のエンティティ
data class User (
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var registered_at: LocalDate? = null,
)