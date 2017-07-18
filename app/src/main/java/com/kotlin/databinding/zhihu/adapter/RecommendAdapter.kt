package com.kotlin.databinding.zhihu.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.databinding.zhihu.BR
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.databinding.HomeBind
import com.kotlin.databinding.zhihu.db.CollectDbHelper
import com.kotlin.databinding.zhihu.model.RecommendData
import com.kotlin.databinding.zhihu.utils.LogUtil
import kotlinx.android.synthetic.main.news_list_item.view.*

/**
 * Created by chawei on 2017/6/20.
 */
class RecommendAdapter(context: Context, var onItemClickListener: (detailUrl: String) -> Unit, var onFlowClickListener: (anchor: View, position: Int, isExist: Boolean) -> Unit) : RecyclerView.Adapter<RecommendAdapter.RecommendHolder>() {
    var mLists = ArrayList<RecommendData>()
    var mContext = context
    var isShow=true

    fun appendList(list: ArrayList<RecommendData>) {
        mLists = list
        notifyDataSetChanged()
    }

    fun removeItem(itemPosition: Int) {
        mLists.removeAt(itemPosition)
        notifyDataSetChanged()
    }

    fun isShowImage(show: Boolean) {
        isShow = show
        LogUtil.debug("adapter中的show---->$show")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendHolder {
        var binding = DataBindingUtil.inflate<HomeBind>(LayoutInflater.from(mContext), R.layout.news_list_item, parent, false)
        var holder = RecommendHolder(binding, binding.root, onItemClickListener, onFlowClickListener)
        return holder
    }

    override fun onBindViewHolder(holder: RecommendHolder, position: Int) {
        holder.bindRecommendData(mLists[position], position)
    }

    override fun getItemCount(): Int = if (mLists != null) mLists.size else 0

    inner class RecommendHolder(binding: HomeBind, itemView: View, val onItemClickListener: (detailUrl: String) -> Unit, val onFlowClickListener: (anchor: View, position: Int, isExist: Boolean) -> Unit) : RecyclerView.ViewHolder(itemView) {
        var bind = binding
        fun bindRecommendData(data: RecommendData, position: Int) {
            bind.setVariable(BR.recommendModel, data)
            LogUtil.debug("bindRecommendData---->$isShow")
            bind.isShowImage =isShow
            itemView.setOnClickListener { onItemClickListener(data.url) }
            bind.root.card_share_overflow.setOnClickListener { onFlowClickListener(bind.root.card_share_overflow, position, CollectDbHelper.collectDbHelper.queryCollectData(data.id)) }
            var list = ArrayList<String>()
            (0..data.label_info.size - 1).mapTo(list) { data.label_info[it].label_name }
            bind.root.title_label.setTags(list)
            list.clear()

        }
    }


}

