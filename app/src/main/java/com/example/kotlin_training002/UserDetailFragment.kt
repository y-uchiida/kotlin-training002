package com.example.kotlin_training002

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlin_training002.databinding.FragmentUserDetailBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // ユーザー一覧画面から受け取ったUser オブジェクトを取得するため、navArgs() でりげーどを用いてargs を設定
    private val args: UserDetailFragmentArgs by navArgs()

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
