package com.android.hsicen.hiltsample.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.android.hsicen.hiltsample.MainActivity

/**
 * 作者：hsicen  2020/8/14 8:33
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class UserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    @SuppressLint("SetTextI18n")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        //手动依赖注入
        val mUser = (context as MainActivity).mUser
        text = "${mUser.name} 的心情变成了：${mUser.mood}"
    }

}