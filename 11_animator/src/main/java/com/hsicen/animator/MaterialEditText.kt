package com.hsicen.animator

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatEditText

/**
 *
 * 作者：Hsicen  2019/7/25 17:16
 *
 * 邮箱：codinghuang@163.com
 *
 * 作用：
 *
 * 描述：HencoderPlus
 */
class MaterialEditText(context: Context, attrs: AttributeSet) :
    AppCompatEditText(context, attrs) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    internal var floatingLabelShown: Boolean = false
    private var floatingLabelFraction: Float = 0.toFloat()
    internal var animator: ObjectAnimator? = null
    internal var useFloatingLabel: Boolean = false
    private var backgroundPadding = Rect()

    init {

        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        useFloatingLabel =
            typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
        typedArray.recycle()

        paint.textSize = TEXT_SIZE
        onUseFloatingLabelChanged()
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (useFloatingLabel) {
                    if (floatingLabelShown && TextUtils.isEmpty(s)) {
                        floatingLabelShown = false
                        getAnimator()!!.reverse()
                    } else if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
                        floatingLabelShown = true
                        getAnimator()!!.start()
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }

    private fun onUseFloatingLabelChanged() {
        background.getPadding(backgroundPadding)
        if (useFloatingLabel) {
            setPadding(
                paddingLeft,
                (backgroundPadding.top.toFloat() + TEXT_SIZE + TEXT_MARGIN).toInt(),
                paddingRight,
                paddingBottom
            )
        } else {
            setPadding(paddingLeft, backgroundPadding.top, paddingRight, paddingBottom)
        }
    }

    fun setUseFloatingLabel(useFloatingLabel: Boolean) {
        if (this.useFloatingLabel != useFloatingLabel) {
            this.useFloatingLabel = useFloatingLabel
            onUseFloatingLabelChanged()
        }
    }

    private fun getAnimator(): ObjectAnimator? {
        if (animator == null) {
            animator =
                ObjectAnimator.ofFloat(this@MaterialEditText, "floatingLabelFraction", 0f, 1f)
        }
        return animator
    }

    fun getFloatingLabelFraction(): Float {
        return floatingLabelFraction
    }

    fun setFloatingLabelFraction(floatingLabelFraction: Float) {
        this.floatingLabelFraction = floatingLabelFraction
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.alpha = (0xff * floatingLabelFraction).toInt()
        val extraOffset = TEXT_ANIMATION_OFFSET * (1 - floatingLabelFraction)
        canvas.drawText(
            hint.toString(),
            TEXT_HORIZONTAL_OFFSET.toFloat(),
            TEXT_VERTICAL_OFFSET + extraOffset,
            paint
        )
    }

    companion object {
        private val TAG = MaterialEditText::class.java.simpleName

        private val TEXT_SIZE = dpToPixel(12f)
        private val TEXT_MARGIN = dpToPixel(8f)
        private val TEXT_VERTICAL_OFFSET = dpToPixel(22f).toInt()
        private val TEXT_HORIZONTAL_OFFSET = dpToPixel(5f).toInt()
        private val TEXT_ANIMATION_OFFSET = dpToPixel(16f).toInt()

        fun dpToPixel(dp: Float): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().displayMetrics
            )
        }
    }
}