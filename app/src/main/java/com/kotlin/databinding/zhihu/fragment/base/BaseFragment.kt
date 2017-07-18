package cn.kotlin.com.kotlinapp.fragment.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.kotlin.com.kotlinapp.KotlinAppApp
import cn.kotlin.com.kotlinapp.model.EventCenter
import com.kotlin.databinding.zhihu.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by chawei on 2017/5/29.
 */
abstract class BaseFragment : Fragment(),View.OnClickListener{
    protected var isAttached=false
    protected var mApp=KotlinAppApp()
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        isAttached=true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if(isRegisterEventBus()){
            EventBus.getDefault().register(this)
        }
        //关闭线程检查
        var policy=StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        mApp=KotlinAppApp.instance
        beforeViewData()
        super.onCreate(savedInstanceState)

    }
    /**
    * EventBus 接收消息
    */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(center: EventCenter<*>) {
        if(isRegisterEventBus()){
            onEventComing(center)
        }
    }

    open fun  onEventComing(center: EventCenter<*>) {

    }

    /**
     * 是否需要注册消息监听
     */
    open fun isRegisterEventBus():Boolean {
        return false
    }

    /**
     * 在View初始化之前，需要初始化数据
     */
    abstract fun beforeViewData()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return initLayout(inflater,container,savedInstanceState)
    }

    /**
     * 初始化布局文件
     */
    abstract fun initLayout(inflater: LayoutInflater?,container: ViewGroup?,savedInstanceState: Bundle?):View

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view,savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    /**
     * 初始化View
     */
    abstract fun initView(view:View?,savedInstanceState: Bundle?)

    override fun onResume() {
        super.onResume()
        if(isResume()){
            requestNetData()
        }
    }

    /**
     * Resume时是否需要刷新数据
     */
    open fun isResume():Boolean{
        return false
    }

    /**
     * 需要手动调用，只有在重写了isResume()并返回true才会主动调用
     */
    abstract fun requestNetData()

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        activity.overridePendingTransition(R.anim.in_to_left, R.anim.out_to_right)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        activity.overridePendingTransition(R.anim.in_to_left, R.anim.out_to_right)
    }

    override fun onDetach() {
        super.onDetach()
        isAttached=false
        if(isRegisterEventBus()){
            EventBus.getDefault().unregister(this)
        }
    }
}