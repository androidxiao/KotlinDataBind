package cn.kotlin.com.kotlinapp.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.ListPopupWindow
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.kotlin.com.kotlinapp.fragment.base.BaseFragment
import cn.kotlin.com.kotlinapp.model.EventCenter
import cn.kotlin.com.kotlinapp.utils.IntConstants
import cn.kotlin.com.kotlinapp.utils.SharePreUtilK
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.activity.user.LoginActivity
import com.kotlin.databinding.zhihu.adapter.HomeListPopAdaper
import com.kotlin.databinding.zhihu.adapter.RecommendAdapter
import com.kotlin.databinding.zhihu.db.CollectDbHelper
import com.kotlin.databinding.zhihu.http.OkhttpClient
import com.kotlin.databinding.zhihu.model.CollectRecomModel
import com.kotlin.databinding.zhihu.model.FlowItemModel
import com.kotlin.databinding.zhihu.model.RecommendData
import com.kotlin.databinding.zhihu.model.RecommendModel
import com.kotlin.databinding.zhihu.tools.Constants
import com.kotlin.databinding.zhihu.tools.JsonParse.parseJson
import com.kotlin.databinding.zhihu.utils.LogUtil
import com.kotlin.databinding.zhihu.utils.Px2DpUtil
import eventbus.com.cn.utils.toast
import kotlinx.android.synthetic.main.fragment_home_layout.*


/**
 * Created by chawei on 2017/5/29.
 */

class HomeFragment : BaseFragment() {
    var count = 0
    var flowItems = ArrayList<FlowItemModel>()
    var collectFlowItems = ArrayList<FlowItemModel>()
    var mLists=ArrayList<RecommendData>()

    lateinit var listPopMenu:ListPopupWindow
    lateinit var adapter:RecommendAdapter
    override fun beforeViewData() {
    }

    override fun initLayout(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return View.inflate(context, R.layout.fragment_home_layout, null)
    }


    override fun initView(view: View?, savedInstanceState: Bundle?) {
        requestNetData()
        bindFlowItem()
        bindCollectFlowItem()
    }

    override fun requestNetData() {
        OkhttpClient.OkPost(Constants.RECOMMEND_URL + count, null, 0, {
            json, requestId ->
            parseRecommend(json)
        })
    }

    fun parseRecommend(json: String) {
        var model = parseJson(json, RecommendModel::class.java)
        LogUtil.debug("model----->${model.list.size}")
        mLists.addAll(model.list)
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        news_list.layoutManager = manager
        adapter = RecommendAdapter(activity, {
            detailUrl ->
            toDetailWeb(detailUrl)
        }, {
            anchor,position,isExists ->
            showFloatMenu(anchor,position,isExists)
        })
        adapter.appendList(model.list)
        news_list.adapter = adapter
    }

    fun toDetailWeb(detailUrl:String) {

    }

    fun bindFlowItem() {
        flowItems.add(FlowItemModel(R.mipmap.ic_launcher, "删除"))
        flowItems.add(FlowItemModel(R.mipmap.ic_launcher, "收藏"))
    }

    fun bindCollectFlowItem(){
        collectFlowItems.add(FlowItemModel(R.mipmap.ic_launcher, "删除"))
        collectFlowItems.add(FlowItemModel(R.mipmap.ic_launcher, "已收藏"))
    }

    fun showFloatMenu(anchor: View,itemPosition: Int,isExists:Boolean) {
        listPopMenu = ListPopupWindow(activity)
        val adapter = HomeListPopAdaper(activity) {
            position ->
            itemClickPosition(position,itemPosition)
        }
        if(isExists){
            adapter.updateList(collectFlowItems)
        }else {
            adapter.appendList(flowItems)
        }
        listPopMenu.setAdapter(adapter)
        listPopMenu.anchorView = anchor
        listPopMenu.width = Px2DpUtil.dp2px(activity, 200.toFloat())
        listPopMenu.height = Px2DpUtil.dp2px(activity, 80.toFloat())
        listPopMenu.isModal = true
        listPopMenu.setDropDownGravity(Gravity.END)
        listPopMenu.show()
    }

    fun itemClickPosition(position: Int,itemPosition:Int) {
        when (position) {
            0 -> delItem(itemPosition)
            1 -> collectItem(itemPosition)
        }
    }

    fun delItem(itemPosition:Int) {
        adapter.removeItem(itemPosition)
        listPopMenu.dismiss()
    }

    fun collectItem(position:Int) {
        val isLogin = SharePreUtilK.getBooleanValue(Constants.IS_LOGIN)
        when (isLogin) {
            true->collectLoginSuc(position)
            false->collectToLogin()
        }

    }

    fun collectLoginSuc(position: Int){
        val data = mLists[position]

        val isExists = CollectDbHelper.collectDbHelper.queryCollectData(data.id)
        if (isExists) {
            cancelCollect(data)
        }else{
            collectRecomItem(data)
        }

    }

    fun cancelCollect(data:RecommendData) {
        val count = CollectDbHelper.collectDbHelper.delCollectData(data.id)
        if (count > 0) {
            context.toast("取消成功")
        }else{
            context.toast("取消失败")
        }
    }

    fun collectRecomItem(data:RecommendData){
        val listLabel = data.label_info
        var types=""
        //拼装label_name放入数据库
        for (i in 0..listLabel.size-1){
            types+=listLabel[i].label_name+","
        }
        var model=CollectRecomModel(data.id,data.title,data.publication_date,data.image_url,data.url,types)
        types=""
        var count=CollectDbHelper.collectDbHelper.insertCollectData(model)
        if(count>0){
            context.toast("收藏成功")
        }else{
            context.toast("收藏失败")
        }
        listPopMenu.dismiss()
    }

    fun collectToLogin(){
        startActivity(Intent(activity,LoginActivity::class.java))
    }

    override fun onClick(v: View?) {

    }


    override fun onEventComing(center: EventCenter<*>) {
        var code=center.eventCode
        when (code) {
            IntConstants.Is_Show_Image->isShowImage(center.data as Boolean)
        }
    }

    override fun isRegisterEventBus(): Boolean {
        return true
    }

    /**
     * 列表是否显示图片
     */
    fun isShowImage(show:Boolean){
        LogUtil.debug("isShowImage---->$show")
        adapter.isShowImage(show)
    }


}
