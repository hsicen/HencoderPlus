package com.hsicen.recyclerviewcore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hsicen.recyclerviewcore.databinding.ActivityMainBinding
import java.util.*

/**
 * <p>作者：Hsicen  2019/8/5 17:27
 * <p>邮箱：codinghuang@163.com
 * <p>功能：
 * <p>描述：RecyclerVIew核心要点讲解
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mData = ArrayList<String>()

    private val mAdapter by lazy {
        FruitAdapter(mData)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      val layoutManager = LinearLayoutManager(this)
      binding.rvFruit.adapter = mAdapter
      binding.rvFruit.layoutManager = layoutManager
      binding.rvFruit.setRecycledViewPool(RecyclerView.RecycledViewPool())

      mData.addAll(listOf("", "", "", "", "", "", "", ""))
      mAdapter.notifyItemChanged(3, "payload")

      binding.rvFruit.recycledViewPool.setMaxRecycledViews(2, 10)
    }
}
