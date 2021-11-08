package com.hsicen.a30_annotation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a30_lib.Binding
import com.hsicen.a30_lib_annotation.BindView

class MainActivity : AppCompatActivity() {

    @BindView(R.id.rootLayout)
    lateinit var mRootLayout: ViewGroup

    @BindView(R.id.tvTitle)
    lateinit var mTvTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Binding.bind(this)

        mTvTitle.text = "初始化过后的值"
        mRootLayout.setBackgroundColor(Color.CYAN)

        mTvTitle.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
