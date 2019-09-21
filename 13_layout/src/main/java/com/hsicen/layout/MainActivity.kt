package com.hsicen.layout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * <p>作者：Hsicen  19-7-28 下午4:13
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：自定义布局
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tagView.setOnClickListener {

            tagView.visibility = View.GONE
        }
    }
}
