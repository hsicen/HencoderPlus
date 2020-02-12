package com.hsicen.a30_annotation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.a30_lib.Binding
import com.hsicen.a30_lib_annotation.BindView

class SecondActivity : AppCompatActivity() {

    @BindView(R.id.tvInfo)
    lateinit var mTvInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Binding.bind(this)

        mTvInfo.text = "这里是第二个页面"
    }
}
