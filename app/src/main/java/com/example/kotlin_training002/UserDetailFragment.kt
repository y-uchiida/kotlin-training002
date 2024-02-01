package com.example.kotlin_training002

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlin_training002.databinding.FragmentUserDetailBinding
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // ユーザー一覧画面から受け取ったUser オブジェクトを取得するため、navArgs() デリゲードを用いてargs を設定
    private val args: UserDetailFragmentArgs by navArgs()

    /**
     *
     */
    private fun updateUser(id: Long, user: JSONObject) {
        val schema = getString(R.string.api_schema)
        val host = getString(R.string.api_host)
        val port = getString(R.string.api_port)
        val url = "$schema://$host:$port/users/$id"

        val requestQueue = Volley.newRequestQueue(context)

        val jsonObjectRequest = object : StringRequest(
            Method.PUT, url,
            Response.Listener {
                response ->
                    val action = UserDetailFragmentDirections.actionUserDetailFragmentToUserListFragment()
                    findNavController().navigate(action)
            },
            Response.ErrorListener {
                error ->
                    Toast.makeText(this.context, "ユーザーの更新に失敗しました", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }

            override fun getBody(): ByteArray {
                return user.toString().toByteArray(Charsets.UTF_8)
            }

            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }

        requestQueue.add(jsonObjectRequest)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)

        val user = args.user

        // ユーザー情報をそれぞれTextViewに設定
        binding.userNameTextEdit.setText(user.name ?: "")
        binding.UserEmailText.text = user.email
        binding.userRegisteredAt.text = user.registered_at.toString()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.user
        user.id ?: return

        binding.btnUserUpdate.setOnClickListener {
            val updatedUser = JSONObject()
            updatedUser.put("name", binding.userNameTextEdit.text.toString())
            // Add other fields as necessary

            updateUser(user.id!!, updatedUser)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
