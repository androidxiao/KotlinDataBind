package com.kotlin.databinding.zhihu.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.kotlin.databinding.zhihu.BR
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.databinding.FlowItemBind
import com.kotlin.databinding.zhihu.model.FlowItemModel
import java.util.*

/**
 * Created by chawei on 2017/6/21.
 */
class HomeListPopAdaper(val context: Context,val onItemClickListener:(position:Int)->Unit) : BaseAdapter() {
    var mItems = ArrayList<FlowItemModel>()
    var binding: FlowItemBind? = null

    fun appendList(items: ArrayList<FlowItemModel>) {
        mItems = items
        notifyDataSetChanged()
    }

    fun updateList(items: ArrayList<FlowItemModel>){
        mItems=items
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = if(mItems!=null) mItems.size else 0

    override fun getItem(position: Int): Any = mItems[position]
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            binding = DataBindingUtil.inflate<FlowItemBind>(LayoutInflater.from(context), R.layout.home_flow_pop_item, parent, false)
            convertView = binding!!.root
            convertView!!.tag = binding
        } else {
            binding = convertView.tag as FlowItemBind
        }
        binding?.setVariable(BR.FlowItem, mItems[position])
        convertView.setOnClickListener { onItemClickListener(position) }
        return convertView
    }

}
