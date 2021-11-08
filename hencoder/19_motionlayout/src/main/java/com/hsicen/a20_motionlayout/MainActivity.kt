package com.hsicen.a20_motionlayout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.hsicen.a20_motionlayout.databinding.ActivitySceneStartBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySceneStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySceneStartBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnStart.setOnClickListener {
            mBinding.ml.transitionToStart()
        }

        mBinding.btnEnd.setOnClickListener {
            mBinding.ml.transitionToEnd()
        }

        mBinding.btnMenu.setOnClickListener {
            startActivity(
                Intent(this, MotionMenuActivity::class.java)
            )
        }

        mBinding.btnConstraint.setOnClickListener {
            startActivity(
                Intent(this, SceneConstraintActivity::class.java)
            )
        }

        mBinding.btnFly.setOnClickListener {
            startActivity(
                Intent(this, CardFlyActivity::class.java)
            )
        }

        mBinding.ml.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

            }

        })
    }
}
