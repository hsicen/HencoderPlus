package com.hsicen.drawable

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

/**
 * <p>作者：Hsicen  2019/7/25 11:13
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：自定义MaterialEditText
 */
class MaterialEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    private val mPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }

    var useLabel = false
        set(value) {
            if (field != value) {
                field = value
                onLabelChanged()
            }
        }
    var mLabel = ""
        set(value) {
            field = value
            invalidate()
        }
    var mLabelSize = 0f
        set(value) {
            field = value
            invalidate()
        }
    var mLabelColor = Color.BLACK
        set(value) {
            field = value
            invalidate()
        }
    private var backPadding = Rect()

    /*** 标记是否显示悬浮标签*/
    private var isLabelShow = false
    /*** 标记动画完成度(0~1) */
    private var labelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    /*** 悬浮标签消失和显示动画*/
    private val mAnimator by lazy {
        ObjectAnimator.ofFloat(this, "labelFraction", 0f, 1f)
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        useLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useLabel, useLabel)
        mLabel = typedArray.getString(R.styleable.MaterialEditText_label) ?: hint.toString()
        mLabelSize = typedArray.getDimension(R.styleable.MaterialEditText_labelSize, 12f.sp2px)
        mLabelColor = typedArray.getColor(R.styleable.MaterialEditText_labelColor, mLabelColor)
        typedArray.recycle()

        onLabelChanged()

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (useLabel)
                    if (isLabelShow && TextUtils.isEmpty(s)) {
                        isLabelShow = false
                        mAnimator.reverse()
                    } else if (!isLabelShow && !TextUtils.isEmpty(s)) {
                        isLabelShow = true
                        mAnimator.start()
                    }
            }
        })
    }

    private fun onLabelChanged() {
        background.getPadding(backPadding)
        if (useLabel) {
            setPadding(
                paddingLeft,
                (backPadding.top.toFloat() + mLabelSize + TEXT_MARGIN).toInt(),
                paddingRight,
                paddingBottom
            )
        } else setPadding(paddingLeft, backPadding.top, paddingRight, paddingBottom)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //Paint先设置Alpha，然后再设置颜色，Alpha效果会失效
        mPaint.color = mLabelColor
        mPaint.textSize = mLabelSize
        mPaint.alpha = (0xff * labelFraction).toInt()
        val extraOffset = HORIZONTAL_OFFSET_EXTRA * (1 - labelFraction)

        val showLength = (mLabel.length * labelFraction).toInt()
        canvas.drawText(
            mLabel.substring(0, showLength),
            HORIZONTAL_OFFSET,
            VERTICAL_OFFSET + extraOffset,
            mPaint
        )
    }

    companion object {
        private val TEXT_MARGIN = 8.dp2px
        private val VERTICAL_OFFSET = 22f.dp2px
        private val HORIZONTAL_OFFSET = 5f.dp2px
        private val HORIZONTAL_OFFSET_EXTRA = 17f.dp2px
    }
}