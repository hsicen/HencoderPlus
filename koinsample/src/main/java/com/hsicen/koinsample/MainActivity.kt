package com.hsicen.koinsample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsicen.koinsample.data.User
import com.hsicen.koinsample.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    private val mUser by inject<User>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //mainBinding.tvUser.text = "${mUser.name}使用Koin的心情是：${mUser.mood}"
        mUser.mood = "奇怪"
    }
}