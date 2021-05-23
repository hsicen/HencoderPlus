package com.hsicen.livedata

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.hsicen.extension.extensions.clickThrottle
import com.hsicen.extension.extensions.dp2px
import com.hsicen.extension.widget.CustomLayout

/**
 * 作者：hsicen  5/20/21 12:27
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：LiveData
 */
class MainActivity : AppCompatActivity() {
  private val container by lazy { ContentLayout(this) }
  private val userModel by lazy {
    ViewModelProvider(this).get(LiveViewModel::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    addContentView(container, ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT))

    container.btnShow.clickThrottle {
      userModel.changeUser()
    }

    userModel.user.observe(this) {
      container.btnShow.text = it.toString()
    }
  }
}

//XML控件布局
@SuppressLint("SetTextI18n")
class ContentLayout(context: Context) : CustomLayout(context) {
  val btnShow = AppCompatButton(context).apply {
    text = "LiveData"
    isAllCaps = false
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    addView(this)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    btnShow.autoMeasure()
    setMeasuredDimension(measuredWidth, measuredHeight)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    btnShow.layout(x = 16.dp2px, y = 16.dp2px)
  }
}