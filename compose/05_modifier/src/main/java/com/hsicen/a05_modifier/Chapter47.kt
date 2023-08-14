package com.hsicen.a05_modifier

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

/******====== 47. addBefore 和 addAfter 的区别 ======******/
/**
 * 同一个 Modifier 的多重身份，先处理那一重身份
 * 区别：
 *  1. addBefore-() 会让里面的 Modifier 身份早于 LayoutModifier 身份被处理，
 *     导致被包含在 LayoutModifier 所处的 ModifiedLayoutNode 的更内部的一层，
 *     从而受到更内部的 LayoutNodeWrapper 的尺寸限制。
 *  2. addAfter-() 会让⾥⾯的 Modifier 身份在 LayoutModifier 身份之后被处理，
 *     这样就和 LayoutModifier 处于同⼀个 ModifiedLayoutNode，
 *     从⽽和 LayoutModifier 的尺⼨范围是⼀样的。
 */

fun ComponentActivity.composeModifier09() {
    setContent {
        ModifierAdd01()
    }
}

@Composable
private fun ModifierAdd01() {

}