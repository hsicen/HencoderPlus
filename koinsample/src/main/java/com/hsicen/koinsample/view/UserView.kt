package com.hsicen.koinsample.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.hsicen.koinsample.data.User
import org.koin.java.KoinJavaComponent.inject

/**
 * 作者：hsicen  2020/8/16 17:53
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class UserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    private val mUser by inject<User>(User::class.java)

    @SuppressLint("SetTextI18n")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        text = "${mUser.name}使用Koin的心情变成了：${mUser.mood}"
    }
}
