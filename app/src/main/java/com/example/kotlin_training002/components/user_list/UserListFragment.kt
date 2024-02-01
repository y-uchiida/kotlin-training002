package com.example.kotlin_training002.components.user_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.kotlin_training002.Entities.User
import com.example.kotlin_training002.R
import com.example.kotlin_training002.databinding.FragmentUserListBinding
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserListFragment : Fragment() {

    /**
     * APIエンドポイントから、ユーザーの一覧を取得
     * APIからのレスポンスは非同期処理で返ってくるので、これを同期的に扱えるように
     * suspend キーワード付きでメソッドを宣言する
     * これにより、APIからのレスポンスを用いて作成したUserのリストを返り値とすることができる
     */
    private suspend fun getUsers(): List<User> {
        val schema = getString(R.string.api_schema)
        val host = getString(R.string.api_host)
        val port = getString(R.string.api_port)
        val url = "$schema://$host:$port/users"

        // suspendCancellableCoroutine で、非同期処理の完了を待つことができる
        // 処理が完了してからreturn が返される
        return suspendCancellableCoroutine { continuation ->
            val request = JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    val users = mutableListOf<User>()
                    for (i in 0 until response.length()) {
                        val jsonObject = response.getJSONObject(i)
                        val user = formatUserFromJson(jsonObject)
                        users.add(user)
                    }
                    continuation.resume(users)
                },
                { error ->
                    continuation.resumeWithException(RuntimeException(error.toString()))
                }
            )
            Volley.newRequestQueue(this.context).add(request)
        }

    }

    /**
     * Json データからUser エンティティを生成する
     */
    private fun formatUserFromJson(json: JSONObject): User {
        // 日付時刻を扱うモジュールを読み込んだjsonMapper オブジェクトを作成
        val jsonMapper = ObjectMapper().registerModule(JavaTimeModule())
        return jsonMapper.readValue(json.toString(), User::class.java)
    }

    // AndroidのView Binding(XMLレイアウトファイル内のビューへの直接参照を提供する機能)を
    // 使用する際に自動的に生成されるクラス
    // FragmentUserListBinding は、 `fragment_user_list.xml` に対応する
    // このクラスを利用することで、レイアウト内のビューに直接アクセスできる
    private var _binding: FragmentUserListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        // UserListFragmentの中にあるユーザー一覧のView (RecyclerView) を取得する
        val userList = binding.UserList

        // ユーザー一覧のView のレイアウトを構成する機能 LayoutManager に、LinearLayoutを扱う機能を持つオブジェクトを指定
        userList.layoutManager = LinearLayoutManager(inflater.context)

        // Coroutineを起動
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val users = getUsers() // ユーザーの一覧を取得
                // UIの更新はメインスレッドで行う必要があるので、withContext()で実行するスレッドを切り替える
                withContext(Dispatchers.Main) {
                    // ユーザー一覧のView とデータを関連付けさせる機能 Adapter を設定
                    // 取得したユーザーの一覧をUserListAdapterに渡す
                    userList.adapter = UserListAdapter(users)
                }
            } catch (e: Exception) {
                // エラーハンドリングはシンプルにトースト通知のみとする
                // UIの更新はメインスレッドで行う必要があるので、withContext()で実行するスレッドを切り替える
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "ユーザー一覧の取得に失敗しました", Toast.LENGTH_LONG).show()
                    // エラーメッセージをそのまま通知に表示するなこんな感じ
                    // Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
