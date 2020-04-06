package com.hsicen.recyclerviewcore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * <p>作者：Hsicen  2019/8/5 17:27
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：RecyclerVIew核心要点讲解
 */
class MainActivity : AppCompatActivity() {

    private val mData = ArrayList<String>()

    private val mAdapter by lazy {
        FruitAdapter(mData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        rvFruit.adapter = mAdapter
        rvFruit.layoutManager = layoutManager
        rvFruit.setRecycledViewPool(RecyclerView.RecycledViewPool())

        mData.addAll(listOf("", "", "", "", "", "", "", ""))
        mAdapter.notifyDataSetChanged()
    }
}
