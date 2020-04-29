package com.hsicen.a25_gradle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        println(resources.displayMetrics.density) //2.75
        println(resources.displayMetrics.scaledDensity) //2.75
        println(resources.displayMetrics.densityDpi) //440
    }
}
