package com.example.kotlin_training002

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.kotlin_training002.Entities.User
import com.example.kotlin_training002.databinding.ActivityUserListBinding
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.json.JSONObject

class UserListActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityUserListBinding

    private fun addMessage(message: String) {
        val container = findViewById<LinearLayout>(R.id.UserListContainer)
        val textView = TextView(this)
        textView.text = message
        textView.textSize = 24F
        container.addView(textView)
    }

    /**
     * ユーザー情報をLinearLayout に追加表示する
     */
    private fun addUserListItem(user: User) {
        val container = findViewById<LinearLayout>(R.id.UserListContainer)
        val textView = TextView(this)
        textView.text = "${user.name}(id: ${user.id}): ${user.email} \n registered date: ${user.registered_at} \n"
        textView.textSize = 16F
        container.addView(textView)
    }

    /**
     * Json データからUser エンティティを生成する
     */
    private fun formatUserFromJson(json: JSONObject): User {
        // 日付時刻を扱うモジュールを読み込んだjsonMapper オブジェクトを作成
        val jsonMapper = ObjectMapper().registerModule(JavaTimeModule())
        return jsonMapper.readValue(json.toString(), User::class.java)
    }

    /**
     * APIエンドポイントから、ユーザーの一覧を取得してLinearLayout に追加する
     */
    private fun getUserRequestHandler() {
        val schema = getString(R.string.api_schema)
        val host = getString(R.string.api_host)
        val port = getString(R.string.api_port)
        val url = "$schema://$host:$port/users"
        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                // 通信成功だった場合の処理:
                // 取得したJson配列からユーザー情報を順番に取り出し、LinearLayout に追加していく
                // JsonArrayはiterable ではないため、for-in ループは使えない
                // 配列要素数を取得するメソッドがあるので、インデックスを用いたループで処理する
                response ->
                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)
                        val user = formatUserFromJson(jsonObject)
                        addUserListItem(user)
                    }

            },
            {error -> addMessage(error.toString())}
        )
        Volley.newRequestQueue(this).add(request)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_user_list)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener {
            view -> addMessage("Hello, world!")
        }

        // ユーザー一覧を取得するAPIエンドポイントとの通信を開始
        getUserRequestHandler()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_user_list)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
