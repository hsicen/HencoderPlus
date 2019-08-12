package com.hsicen.a19_constraintlayout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a19_constraintlayout.constraint.ConstraintActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnConstraint.setOnClickListener {
            startActivity(
                Intent(this, ConstraintActivity::class.java)
            )
        }

        btnMotion.setOnClickListener {
            startActivity(Intent(this, MotionActivity::class.java))
        }
    }
}
