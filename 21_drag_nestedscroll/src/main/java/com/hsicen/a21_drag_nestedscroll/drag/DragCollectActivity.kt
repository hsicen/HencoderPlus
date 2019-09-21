package com.hsicen.a21_drag_nestedscroll.drag

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.hsicen.a21_drag_nestedscroll.R
import kotlinx.android.synthetic.main.activity_drag_collect.*

/**
 * <p>作者：Hsicen  2019/9/9 15:55
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：View.onDragListener的使用
 * View.startDrag()  开启拖拽
 * View.setOnDragListener() 设置拖拽监听
 */
class DragCollectActivity : AppCompatActivity() {

    /*** 开始拖拽监听*/
    private val onDragStart by lazy {
        View.OnLongClickListener {
            val clipData = ClipData.newPlainText("name", it.contentDescription)
            ViewCompat.startDragAndDrop(it, clipData, View.DragShadowBuilder(it), null, 0)
        }
    }

    /*** 拖拽监听*/
    private val onDragListener by lazy {
        View.OnDragListener { v, event ->
            //拖拽状态监听
            when (event.action) {
                //用户释放了DragShadow
                DragEvent.ACTION_DROP -> {
                    if (v.id == R.id.llCollect) {
                        val dragView = TextView(this)
                        dragView.textSize = 16f
                        dragView.setTextColor(resources.getColor(R.color.colorAccent))
                        dragView.setPadding(8, 8, 8, 8)
                        dragView.text = event.clipData.getItemAt(0).text
                        (v as LinearLayout).addView(dragView)
                    }
                }
            }

            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_collect)

        ivHead.setOnLongClickListener(onDragStart)
        ivLogo.setOnLongClickListener(onDragStart)
        llCollect.setOnDragListener(onDragListener)
    }
}
