package com.hsicen.a04_animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author: hsicen
 * @date: 2022/3/28 10:07
 * @email: codinghuang@163.com
 * description:
 *
 * animateXXXAsState 动画 -> 对动画的目标值进行控制，适用于状态切换
 * Animatable 动画 -> 对动画过程进行控制，适用于动画流程定制
 *
 * AnimationSpec 继承关系
 *  1.FiniteAnimationSpec
 *    (1).DurationBasedAnimationSpec
 *      ①.TweenSpec
 *      ②.SnapSpec
 *      ③.KeyframesSpec
 *    (2).RepeatableSpec
 *    (3).SpringSpec
 *  2.FloatAnimationSpec
 *    (1).FloatSpringSpec
 *    (2).FloatTweenSpec
 *  3.InfiniteAnimationSpec
 */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    composeAnimation14()
  }
}