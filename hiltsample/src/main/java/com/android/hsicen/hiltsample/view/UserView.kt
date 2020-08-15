package com.android.hsicen.hiltsample.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.android.hsicen.hiltsample.data.User
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * 作者：hsicen  2020/8/14 8:33
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */

@AndroidEntryPoint
class UserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    @Inject
    lateinit var mUser: User

    @SuppressLint("SetTextI18n")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        text = "${mUser.name} 的心情变成了：${mUser.mood}"
    }

}