package com.example.kotlin_training002

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

// アクティビティの共通クラス
open class BaseActivity: AppCompatActivity() {

    // メニューのインフレート
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("BaseActivity", "onCreateOptionsMenu on BaseActivity")
        menuInflater.inflate(R.menu.my_app_menu, menu)
        return true
    }

    // メニュー項目がクリックされたときの動作
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // クリックされたメニュー項目のIDによって、遷移先のアクティビティを切り替える
        return when (item.itemId) {
            R.id.menuItemMain -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.menuItemMyCalendar -> {
                startActivity(Intent(this, MyCalendarActivity::class.java))
                true
            }
            R.id.menuItemHighAndLowGame -> {
                startActivity(Intent(this, HighAndLowGameActivity::class.java))
                true
            }
            R.id.menuItemUserList -> {
                startActivity(Intent(this, UserListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}