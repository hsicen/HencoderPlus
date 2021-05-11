package com.hsicen.a19_constraintlayout.constraint

import android.os.Bundle
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.hsicen.a19_constraintlayout.R
import com.hsicen.a19_constraintlayout.databinding.ActivityConstraintHelperBinding

class ConstraintHelperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConstraintHelperBinding

    private val mConstraintSet1 by lazy { ConstraintSet() }
    private val mConstraintSet2 by lazy { ConstraintSet() }
    private var mOld = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstraintHelperBinding.inflate(layoutInflater)

        //Clone from layout Resource file
        mConstraintSet2.clone(this, R.layout.layout_set_type)
        setContentView(binding.root)

        //Clone from ConstraintLayout
        mConstraintSet1.clone(binding.clType1)

        binding.tvName.setOnClickListener {
            changeLayout()
            mOld = !mOld
        }
    }

    private fun changeLayout() {
        TransitionManager.beginDelayedTransition(binding.clType1)

        if (mOld) {
            mConstraintSet1.applyTo(binding.clType1)
        } else {
            mConstraintSet2.applyTo(binding.clType1)
        }
    }
}
