package com.kotlin.databinding.zhihu.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.databinding.zhihu.BR
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.R.layout
import com.kotlin.databinding.zhihu.databinding.CollectBind
import com.kotlin.databinding.zhihu.model.CollectRecomModel
import com.kotlin.databinding.zhihu.utils.LogUtil
import com.kotlin.databinding.zhihu.utils.StringUtils
import kotlinx.android.synthetic.main.collect_list_item.view.*

/**
 * Created by chawei on 2017/6/26.
 */
class CollectDataAdapter(context: Context, var onItemClickListener: (position: Int) -> Unit, var onItemLongClickListener: (position: Int) -> Boolean) : RecyclerView.Adapter<CollectDataAdapter.CollectHolder>() {
    val mContext = context
    var collects = ArrayList<CollectRecomModel>()

    fun appendList(collects: ArrayList<CollectRecomModel>) {
        this.collects = collects
        notifyDataSetChanged()
        LogUtil.debug("data---->${collects.size}")
    }

    fun clearAllData(){
        collects.clear()
        notifyDataSetChanged()

    }

    fun removeItem(position: Int){
        collects.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CollectHolder {
        var binding = DataBindingUtil.inflate<CollectBind>(LayoutInflater.from(mContext), R.layout.collect_list_item, parent, false)
        var view = binding.root
        return CollectHolder(binding, view, onItemClickListener, onItemLongClickListener)
    }

    override fun onBindViewHolder(holder: CollectHolder, position: Int) {
        holder.bindData(collects[position], position)
    }

    override fun getItemCount(): Int = collects.size


    class CollectHolder(binding: ViewDataBinding, itemView: View, var onItemClickListener: (position: Int) -> Unit, var onItemLongClickListener: (position: Int) -> Boolean) : RecyclerView.ViewHolder(itemView) {
        var bind = binding
        fun bindData(data: CollectRecomModel, position: Int) {
            bind.root.id_rl_collect.setOnClickListener { onItemClickListener(position) }
            bind.root.id_rl_collect.setOnLongClickListener { onItemLongClickListener(position) }
            bind.setVariable(BR.collectModel,data)
            itemView.title_label.setTags(StringUtils.delEndPoint( data.types))
        }
    }
}