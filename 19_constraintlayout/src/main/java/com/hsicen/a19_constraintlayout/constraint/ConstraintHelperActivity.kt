package com.hsicen.a19_constraintlayout.constraint

import android.os.Bundle
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.hsicen.a19_constraintlayout.R
import kotlinx.android.synthetic.main.activity_constraint_helper.*

class ConstraintHelperActivity : AppCompatActivity() {

    private val mConstraintSet1 by lazy { ConstraintSet() }
    private val mConstraintSet2 by lazy { ConstraintSet() }
    private var mOld = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Clone from layout Resource file
        mConstraintSet2.clone(this, R.layout.layout_set_type)
        setContentView(R.layout.activity_constraint_helper)

        //Clone from ConstraintLayout
        mConstraintSet1.clone(clType1)

        tvName.setOnClickListener {
            changeLayout()
            mOld = !mOld
        }
    }

    private fun changeLayout() {
        TransitionManager.beginDelayedTransition(clType1)

        if (mOld) {
            mConstraintSet1.applyTo(clType1)
        } else {
            mConstraintSet2.applyTo(clType1)
        }
    }
}
