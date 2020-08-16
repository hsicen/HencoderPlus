package com.hsicen.a36_launch_mode.singleTask

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a36_launch_mode.R
import kotlinx.android.synthetic.main.activity_single_task1.*

class SingleTask1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_task1)

        btn_jump2.setOnClickListener {
            startActivity(Intent(this, SingleTask2Activity::class.java))
        }
    }
}
