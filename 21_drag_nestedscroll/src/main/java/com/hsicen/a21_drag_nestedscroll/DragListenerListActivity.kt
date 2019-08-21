package com.hsicen.a21_drag_nestedscroll

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drag_listener_list.*

/**
 * <p>作者：Hsicen  2019/8/21 17:00
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：拖动排序
 */
class DragListenerListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_listener_list)

        ivDrag.setOnLongClickListener {

            true
        }
    }
}
