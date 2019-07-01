package com.hsicen.drawing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * <p>作者：Hsicen  6/22/2019 12:32 PM
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：自定义View
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {

            sportView.startProgress()
        }
    }
}
