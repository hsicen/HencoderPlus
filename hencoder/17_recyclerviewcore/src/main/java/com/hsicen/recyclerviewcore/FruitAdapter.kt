package com.hsicen.recyclerviewcore

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
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

    /***  多布局逻辑处理*/
    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            1
        } else 2
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

    fun notifyDataChange() {
        val oldData = listOf(1, 2, 3)
        val newData = listOf(1, 2, 3)

        val diffBack = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize() = oldData.size

            override fun getNewListSize() = newData.size

            //比较两个位置是否是同类型Item，相同则调用areContentsTheSame
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val old = oldData[oldItemPosition]
                val new = newData[newItemPosition]

                return old == new
            }

            //比较内容是否相同， 相同则直接复用，不同则调用getChangePayload差异化更新
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

                return false
            }

        })

        //应用到Adapter更新数据
        diffBack.dispatchUpdatesTo(this)
    }
}