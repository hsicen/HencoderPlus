package com.hsicen.a19_constraintlayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGone.setOnClickListener {
            if (tv1.isVisible) {
                tv1.visibility = View.GONE
            } else {
                tv1.visibility = View.VISIBLE
            }
        }
    }
}
