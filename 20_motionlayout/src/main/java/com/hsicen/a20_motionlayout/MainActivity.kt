package com.hsicen.a20_motionlayout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_scene_start.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_start)


        btnStart.setOnClickListener {
            ml.transitionToStart()
        }

        btnEnd.setOnClickListener {
            ml.transitionToEnd()
        }

        btnMenu.setOnClickListener {
            startActivity(
                Intent(this, MotionMenuActivity::class.java)
            )
        }

        btnConstraint.setOnClickListener {
            startActivity(
                Intent(this, SceneConstraintActivity::class.java)
            )
        }

        ml.setTransitionListener(object : MotionLayout.TransitionListener {
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
