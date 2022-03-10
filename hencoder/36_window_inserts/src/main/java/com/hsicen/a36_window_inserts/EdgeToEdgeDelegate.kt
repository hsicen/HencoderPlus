package com.hsicen.a36_window_inserts

import android.graphics.Color
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import com.hsicen.extension.extensions.isDarkTheme

/**
 * WindowInserts edge-to-edge 适配
 */
class EdgeToEdgeDelegate(private val activity: FragmentActivity) {
  private var everGivenInsetsToDecorView = false

  fun start() {
    activity.window.navigationBarColor = if (isDarkTheme(activity)) {
      Color.BLACK
    } else {
      Color.WHITE
    }

    WindowCompat.setDecorFitsSystemWindows(activity.window, false)
    everGivenInsetsToDecorView = false
    activity.window.decorView.doOnApplyWindowInsets { windowInsets, _, _ ->
      val insets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars())
      val gestureNavigation = isGestureNavigation(insets)

      if (gestureNavigation.not()) {
        // Let decorView draw the translucent navigationBarColor
        ViewCompat.onApplyWindowInsets(activity.window.decorView, windowInsets)
        everGivenInsetsToDecorView = true
      } else if (gestureNavigation && everGivenInsetsToDecorView) {
        // Let decorView remove navigationBarBackground once it has been draw
        ViewCompat.onApplyWindowInsets(
          activity.window.decorView, WindowInsetsCompat.Builder()
            .setInsets(
              WindowInsetsCompat.Type.navigationBars(),
              Insets.of(insets.left, insets.top, insets.right, 0)
            )
            .build()
        )
      } else {
        // Because we intercepted the onApplyWindowInsets of decorView,
        // navigationBarColor will not be used,
        // which means that the navigation bar will be completely transparent.
      }
    }
  }

  private fun isGestureNavigation(navigationBarsInsets: Insets): Boolean {
    val threshold = (20 * activity.resources.displayMetrics.density).toInt()
    return navigationBarsInsets.bottom <= threshold.coerceAtLeast(44)
  }
}