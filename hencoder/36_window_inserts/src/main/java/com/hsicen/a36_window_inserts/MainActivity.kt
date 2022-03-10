package com.hsicen.a36_window_inserts

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.hsicen.extension.extensions.clickThrottle
import com.hsicen.extension.toast.info
import com.hsicen.extension.widget.CustomLayout
import com.hsicen.extension.widget.wrapContent

/**
 * @author: hsicen
 * @date: 2022/3/10 14:58
 * @email: codinghuang@163.com
 * description: WindowInserts处理
 *
 * SystemWindow:
 *  1. statusBars
 *  2. navigationBars
 *  3. ime
 *  4. systemGestures
 *  5. systemBars
 *  ....
 *
 *  dispatchApplyWindowInsets: 分发流程
 *  1. 9.0   只要其中一个 View 或 ViewGroup 消耗掉，就不会往下分发
 *  2. 10.0  所有的 View 都会得到这个事件
 *  3. 11.0  ViewGroup 会进行判断这个事件是否被消耗掉，只要 ViewGroup 本身不消费，
 *    其同级别 ViewGroup 及其子 View 都会收到这个事件
 *
 * onApplyWindowInsetsListener & onApplyWindowInsets 来自定义处理 Insets
 *
 * what can we do with insets.
 * 1. edge-to-edge adapt.
 * 2. smooth soft keyboard transition.
 */
class MainActivity : AppCompatActivity() {
  private val rootView by lazy { ContentView(this) }

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(rootView)
    EdgeToEdgeDelegate(this).start()

    rootView.btnShow.text = "WindowInserts"
    rootView.btnShow.clickThrottle {
      info("WindowInserts 设置", this)
    }
  }

  class ContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
  ) : CustomLayout(context, attrs, defStyleAttr) {
    val btnShow = AppCompatButton(context).apply {
      isAllCaps = false
      layoutParams = LayoutParams(wrapContent, wrapContent)
      addView(this)
    }

    override fun onMeasureChildren(widthMeasureSpec: Int, heightMeasureSpec: Int) {
      btnShow.autoMeasure()
      setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
      btnShow.layout(x = horizontalCenterX(btnShow), y = verticalCenterTop(btnShow))
    }

    override fun dispatchApplyWindowInsets(insets: WindowInsets?): WindowInsets {
      return super.dispatchApplyWindowInsets(insets)
    }
  }
}