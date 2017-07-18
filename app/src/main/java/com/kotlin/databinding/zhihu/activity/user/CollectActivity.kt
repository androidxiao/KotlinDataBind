package com.kotlin.databinding.zhihu.activity.user

import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import cn.kotlin.com.kotlinapp.activity.base.BaseActivity
import cn.kotlin.com.kotlinapp.utils.DialogUtil
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.adapter.CollectDataAdapter
import com.kotlin.databinding.zhihu.databinding.CollectBindA
import com.kotlin.databinding.zhihu.db.CollectDbHelper
import com.kotlin.databinding.zhihu.model.CollectRecomModel
import eventbus.com.cn.utils.toast
import kotlinx.android.synthetic.main.activitiy_collect_layout.*
import kotlinx.android.synthetic.main.title_header_layout.*

/**
 * Created by chawei on 2017/6/26.
 */
class CollectActivity : BaseActivity() {

    var collectBind: CollectBindA? = null
    var data = ArrayList<CollectRecomModel>()
    lateinit var adapter: CollectDataAdapter
    override fun beforeViewData() {

    }

    override fun initLayoutView() {
        collectBind = DataBindingUtil.setContentView<CollectBindA>(this, R.layout.activitiy_collect_layout)
        bindListener()
    }

    fun bindListener() {
        title_left_btn.setOnClickListener(this)
        title_right_btn.setOnClickListener(this)
    }

    override fun requestNetData() {
        data = CollectDbHelper.collectDbHelper.queryAllData()
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        id_collect_recycle.layoutManager = manager
        adapter = CollectDataAdapter(this, {
            position ->
            itemClick()
        }) {
            position ->
            itemLognClick(position)
        }
        adapter?.appendList(data)
        id_collect_recycle.adapter = adapter
    }

    fun itemClick() {
    }

    fun itemLognClick(position: Int): Boolean {
        DialogUtil.instance.crateDefaultDailog(this, "删除这条收藏", {
            val model = data[position]
            val count = CollectDbHelper.collectDbHelper.delCollectData(model.titleId)
            if (count > 0) {
                toast("删除成功")
                adapter?.removeItem(position)
            } else {
                toast("删除失败，请稍后重试/(ㄒoㄒ)/~~")
            }
        })
        return true
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.title_left_btn -> finish()
            R.id.title_right_btn -> clearAllCollect()
        }
    }

    /**
     * 删除全部收藏
     */
    fun clearAllCollect() {
        DialogUtil.instance.crateDefaultDailog(this, "删除全部收藏", {
            val count = CollectDbHelper.collectDbHelper.delAllCollectData()
            if (count > 0) {
                toast("删除成功")
                adapter?.clearAllData()
            } else {
                toast("删除失败，请稍后重试/(ㄒoㄒ)/~~")
            }
        })
    }
}