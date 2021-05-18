package com.hsicen.daggersample.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.hsicen.daggersample.DaggerApp
import com.hsicen.daggersample.data.User
import javax.inject.Inject

/**
 * 作者：hsicen  2020/8/16 16:13
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
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

        //加入编织图
        (context.applicationContext as DaggerApp).coreComponent.inject(this)
        text = "${mUser.name}使用Dagger的心情变成了：${mUser.mood}"
    }
}