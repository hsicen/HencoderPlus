package com.hsicen.a21_drag_nestedscroll

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * <p>作者：Hsicen  2019/8/20 16:00
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：View 拖拽和滑动冲突处理
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDragCollect.setOnClickListener {
            startActivity(Intent(this, DragCollectActivity::class.java))
        }

        tvDragUpDown.setOnClickListener {
            startActivity(Intent(this, DragUpDownActivity::class.java))
        }
    }
}
