package com.hsicen.recyclerviewcore

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * <p>作者：Hsicen  2019/8/6 9:33
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
class FruitAdapter(private val data: List<String>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    /*** 每个对应的ItemView只会调用一次该方法*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //itemView的创建与销毁   在这里进行优化
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv_apple, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    /*** 列表滑动会多次调用该方法*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //数据绑定
        holder.bindTo(position)
    }

    /*** 增量更新方法*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)


    }

    /***  多布局逻辑处理*/
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            1
        } else 2
    }

    /*** 可在此方法中处理由缓存引发的相关问题*/
    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
    }

    @SuppressLint("SetTextI18n")
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //itemView 控件初始化
        private val mEdition: TextView = itemView.findViewById(R.id.tvEdition)
        val mContent: TextView = itemView.findViewById(R.id.tvContent)

        /*** 数据绑定处理*/
        fun bindTo(position: Int) {
            mEdition.text = "第20190606期 $position"
        }
    }
}