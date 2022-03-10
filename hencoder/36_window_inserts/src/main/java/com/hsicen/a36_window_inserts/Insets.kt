package com.hsicen.a36_window_inserts

import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.core.view.*

class InitialPadding(
  val left: Int,
  val top: Int,
  val right: Int,
  val bottom: Int
)

class InitialMargin(
  val left: Int,
  val top: Int,
  val right: Int,
  val bottom: Int
)


fun recordInitialPaddingForView(view: View) = InitialPadding(
  view.paddingLeft,
  view.paddingTop,
  view.paddingRight,
  view.paddingBottom
)

fun recordInitialMarginForView(view: View) = InitialMargin(
  view.marginLeft,
  view.marginTop,
  view.marginRight,
  view.marginBottom
)


fun View.doOnApplyWindowInsets(block: (WindowInsetsCompat, InitialPadding, InitialMargin) -> Unit) {
  val initialPadding = recordInitialPaddingForView(this)
  val initialMargin = recordInitialMarginForView(this)

  ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
    block(insets, initialPadding, initialMargin)
    insets
  }

  // requestApplyInsetsWhenAttached()
  doOnAttach {
    requestApplyInsets()
  }
}


fun View.requestApplyInsetsWhenAttached() {
  if (isAttachedToWindow) {
    requestApplyInsets()
  } else {
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
      override fun onViewAttachedToWindow(v: View?) {
        v?.removeOnAttachStateChangeListener(this)
        v?.requestApplyInsets()
      }

      override fun onViewDetachedFromWindow(v: View?) = Unit
    })
  }
}

fun View.applyBottomWindowInsetsForScrollingView(scrollingView: ViewGroup) {
  scrollingView.clipToPadding = false
  val padding = recordInitialPaddingForView(scrollingView)
  this.doOnApplyWindowInsets { windowInsets, _, _ ->
    scrollingView.updatePadding(bottom = padding.bottom + windowInsets.systemWindowInsetBottom)
  }
}


fun View.applyIme() {
  if (Build.VERSION.SDK_INT >= 30) {
    ViewCompat.setWindowInsetsAnimationCallback(
      this,
      object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_STOP) {
        private var isImeVisible = false

        override fun onPrepare(animation: WindowInsetsAnimationCompat) {
          super.onPrepare(animation)
          isImeVisible =
            ViewCompat.getRootWindowInsets(this@applyIme)?.isVisible(WindowInsetsCompat.Type.ime())
              ?: false
        }

        override fun onProgress(
          insets: WindowInsetsCompat,
          runningAnimations: MutableList<WindowInsetsAnimationCompat>
        ): WindowInsetsCompat {
          val typesInset = insets.getInsets(WindowInsetsCompat.Type.ime())
          if (!isImeVisible) {
            // fooVIew.translationY = fooView.height - typesInsets.bottom + ...
          }

          return insets
        }

        override fun onEnd(animation: WindowInsetsAnimationCompat) {
          super.onEnd(animation)
          // fooView.translationY = 0f
        }
      })
  }
}