package com.example.kotlin_training002.components.user_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_training002.Entities.User
import com.example.kotlin_training002.R

/**
 * RecyclerView.Adapter:
 * RecyclerView にデータを関連づけさせるクラス
 * user のデータの内容を保持するため、コンストラクタでユーザーのリストを受け取る
 */
class UserListAdapter(
    private val users: List<User>
): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    /**
     * 1件分のViewをつくり、ViewHolderに渡す
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(root)
    }

    /**
     * Adapterが管理しているデータの件数を返す
     * データの件数 = users プロパティのListの長さ
     */
    override fun getItemCount(): Int {
        return users.size
    }

    /**
     * RecyclerViewが持っているViewHolderのデータ1件ごとに、
     * Viewに表示する内容をセットする
     * position でリストに中のインデックス番号が渡される
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // インデックスに該当するユーザーをリストから取得
        val user = users[position]

        // View(user_list_item) のuserNameText とuserEmailText に、user の値を渡す
        holder.userNameText.text = user.name
        holder.userEmailText.text = user.email
    }

    /**
     * RecyclerView.ViewHolder:
     * データ1件分のViewを保持し、リストの中にどんなデータがあるかをそれぞれ管理する
     * View オブジェクトを引数で受け取るので、中に入るView(レイアウト)を用意しておく
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val userNameText: TextView = view.findViewById(R.id.userNameText)
        val userEmailText: TextView = view.findViewById(R.id.userEmailText)
    }
}
