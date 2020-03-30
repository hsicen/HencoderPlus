package com.hsicen.recyclerviewcore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.hsicen.recyclerviewcore.manager.CustomLayoutManager
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

        val layoutManager = CustomLayoutManager(0.7f, 0.85f)
        rvFruit.adapter = mAdapter
        rvFruit.layoutManager = layoutManager
        rvFruit.setRecycledViewPool(RecyclerView.RecycledViewPool())

        mData.addAll(listOf("", "", "", "", "", "", "", ""))
        mAdapter.notifyDataSetChanged()
    }
}
