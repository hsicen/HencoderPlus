package com.hsicen.a19_constraintlayout.constraint

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a19_constraintlayout.R
import kotlinx.android.synthetic.main.activity_constraint.*

class ConstraintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)

        btnRelative.setOnClickListener {
            startActivity(
                Intent(this, ConstraintPositionActivity::class.java)
            )
        }

        btnHelper.setOnClickListener {
            startActivity(Intent(this, ConstraintHelperActivity::class.java))
        }
    }
}
