package com.hsicen.animator

import android.animation.*
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener
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

        //几何变换与属性动画结合
        cameraStart.setOnClickListener {
            val bottomFlip = ObjectAnimator.ofFloat(cameraView, "mBottomFlip", 45f)
            bottomFlip.duration = 1000

            val rotate = ObjectAnimator.ofFloat(cameraView, "mRotate", 270f)
            rotate.duration = 1000
            rotate.startDelay = 200

            val topFlip = ObjectAnimator.ofFloat(cameraView, "mTopFlip", -45f)
            topFlip.duration = 1000
            topFlip.startDelay = 200


            val bottomHolder = PropertyValuesHolder.ofFloat("mBottomFlip", 0f)
            val topHolder = PropertyValuesHolder.ofFloat("mTopFlip", 0f)
            val endFlip = ObjectAnimator.ofPropertyValuesHolder(
                cameraView,
                bottomHolder,
                topHolder
            )
            endFlip.duration = 100
            endFlip.startDelay = 200

            val set = AnimatorSet()
            set.playSequentially(bottomFlip, rotate, topFlip, endFlip)
            set.start()
            set.addListener(onEnd = {
                cameraView.mRotate = 0f
            })
        }

        //自定义估值器
        pointStart.setOnClickListener {
            pointView.mPoint = PointF(25f.dp2px, 25f.dp2px)
            pointView.mColor = Color.parseColor("#ff0000")

            val positionHolder =
                PropertyValuesHolder.ofObject(
                    "mPoint",
                    PointEvaluator(),
                    PointF(325f.dp2px, 325f.dp2px)
                )
            val colorHolder = PropertyValuesHolder.ofInt("mColor", Color.parseColor("#00ff00"))
            colorHolder.setEvaluator(ArgbEvaluator())

            val pointAnimator =
                ObjectAnimator.ofPropertyValuesHolder(pointView, positionHolder, colorHolder)
            pointAnimator.duration = 3000
            pointAnimator.interpolator = AccelerateInterpolator()

            pointAnimator.start()
            pointAnimator.addListener(onEnd = {
                pointView.mPoint = PointF(25f.dp2px, 25f.dp2px)
                pointView.mColor = Color.parseColor("#ff0000")
            })
        }

        //字符串动画
        provinceStart.setOnClickListener {
            val provinceAnimator =
                ObjectAnimator.ofObject(provinceView, "mProvince", ProvinceEvaluator(), "广西省")
                    .setDuration(10000L)

            provinceAnimator.addListener(onEnd = {
                provinceView.mProvince = "北京市"
            })

            provinceAnimator.start()
        }

        colorStart.setOnClickListener {
            colorView.startAnimate()
        }

        sportStart.setOnClickListener {
            sportView.startProgress()
        }
    }

    inner class ProvinceEvaluator : TypeEvaluator<String> {
        private val mData = listOf(
            "北京市",
            "1天津市",
            "1上海市",
            "4重庆市",
            "5四川省",
            "6河北省",
            "7河南省",
            "8山西省",
            "9广东省",
            "10广西省",
            "11贵州省",
            "2北京市",
            "2天津市",
            "3上海市",
            "4重庆市",
            "5四川省",
            "6河北省",
            "7河南省",
            "8山西省",
            "9广东省",
            "10广西省",
            "11贵州省",
            "1北京市",
            "2天津市",
            "3上海市",
            "4重庆市",
            "5四川省",
            "6河北省",
            "7河南省",
            "8山西省",
            "9广东省",
            "10广西省",
            "11贵州省",
            "1北京市",
            "2天津市",
            "2上海市",
            "4重庆市",
            "5四川省",
            "6河北省",
            "7河南省",
            "8山西省",
            "9广东省",
            "10广西省",
            "广西省"
        )

        override fun evaluate(fraction: Float, startValue: String, endValue: String): String {

            val startIndex = mData.indexOf(startValue)
            val endIndex = mData.indexOf(endValue)
            val currentIndex = startIndex + (endIndex - startIndex) * fraction

            return mData[currentIndex.toInt()]
        }
    }

}
