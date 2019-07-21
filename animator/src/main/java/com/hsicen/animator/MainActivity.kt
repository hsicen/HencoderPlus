package com.hsicen.animator

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * <p>作者：Hsicen  2019/7/19 10:52
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：自定义View动画
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            cameraView.mPoint = PointF(25f.dp2px, 25f.dp2px)
            cameraView.mColor = Color.parseColor("#ff0000")

            val widthPixels = Resources.getSystem().displayMetrics.widthPixels - 25f.dp2px
            val heightPixels = Resources.getSystem().displayMetrics.heightPixels - 25f.dp2px

            val positionHolder =
                    PropertyValuesHolder.ofObject("mPoint", PointEvaluator(), PointF(widthPixels, heightPixels))
            val colorHolder = PropertyValuesHolder.ofInt("mColor", Color.parseColor("#00ff00"))
            colorHolder.setEvaluator(ArgbEvaluator())

            val pointAnimator = ObjectAnimator.ofPropertyValuesHolder(cameraView, positionHolder, colorHolder)
            pointAnimator.duration = 3000
            pointAnimator.interpolator = AccelerateInterpolator()

            pointAnimator.start()
        }
    }
}
