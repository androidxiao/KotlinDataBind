package cn.kotlin.com.kotlinapp.fragment

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.kotlin.com.kotlinapp.fragment.base.BaseFragment
import cn.kotlin.com.kotlinapp.model.EventCenter
import cn.kotlin.com.kotlinapp.utils.IntConstants
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.activity.user.CollectActivity
import com.kotlin.databinding.zhihu.databinding.PersonBind
import com.kotlin.databinding.zhihu.db.CollectDbHelper
import kotlinx.android.synthetic.main.fragment_person_layout.*
import kotlinx.android.synthetic.main.is_show_image_item.*
import org.greenrobot.eventbus.EventBus


/**
 * Created by chawei on 2017/5/29.
 */

class PersonFragment : BaseFragment() {

    override fun beforeViewData() {
    }

    override fun initLayout(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var bind=DataBindingUtil.inflate<PersonBind>(LayoutInflater.from(activity), R.layout.fragment_person_layout,container,false)
        bind.personFragmentListener=this
        return  bind.root
    }

    fun bindListener(){
        id_comm_collect.setOnClickListener(this)
        id_ck_show_image.setOnCheckedChangeListener { buttonView, isChecked ->
            when(isChecked){
                true->isShowImage(true)
                false->isShowImage(false)
            }
        }
    }

    fun isShowImage(show:Boolean){
        EventBus.getDefault().post(EventCenter(IntConstants.Is_Show_Image,show))
    }


    override fun initView(view: View?, savedInstanceState: Bundle?) {
        bindListener()
    }

    override fun requestNetData() {
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.id_comm_collect->collect()
        }
    }

    fun collect(){
        startActivity(Intent(activity,CollectActivity::class.java))
    }


    override fun onResume() {
        super.onResume()
        isHaveCollect()
    }

    /**
     * 是否有收藏内容
     */
    fun isHaveCollect(){
        val count = CollectDbHelper.collectDbHelper.isHaveContent()
        if (count > 0) {
            id_comm_collect.setHint("有收藏")
        }else{
            id_comm_collect.setHint("未收藏")
        }
    }

    override fun isResume():Boolean{
        return true
    }

    override fun onEventComing(center: EventCenter<*>) {
        super.onEventComing(center)
    }

    override fun isRegisterEventBus(): Boolean {
        return true
    }

}
