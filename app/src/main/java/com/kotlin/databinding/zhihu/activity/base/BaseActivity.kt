package cn.kotlin.com.kotlinapp.activity.base

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.View
import cn.kotlin.com.kotlinapp.model.EventCenter
import cn.kotlin.com.kotlinapp.utils.ActivityUtilK
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.utils.LogUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by chawei on 2017/5/27.
 */
 abstract class BaseActivity: AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        var policy=StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        if(isRegisterEventBus()){
            EventBus.getDefault().register(this)
        }
        super.onCreate(savedInstanceState)
        beforeViewData()
        initLayoutView()
        ActivityUtilK.addActivity(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        requestNetData()
    }

    protected abstract fun beforeViewData()

    protected abstract fun initLayoutView()

    protected abstract fun requestNetData()


    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransition(R.anim.in_to_left,R.anim.out_to_right)
    }

    /**
     * 和setResult相同
     */
    protected fun setResultForAnimation(intent: Intent,resultCode:Int){
        super.setResult(resultCode,intent)
        finish()
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.exit_to_right,R.anim.exit_to_left)
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        overridePendingTransition(R.anim.in_to_left,R.anim.out_to_right)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        ActivityUtilK.removeActivity(this)
        if(isRegisterEventBus()){
            EventBus.getDefault().unregister(this)
        }
        super.onDestroy()
    }

    /**
     * EventBus 接收消息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(center: EventCenter<*>) {
        LogUtil.debug("isRegisterEventBus-->"+isRegisterEventBus())
        if(isRegisterEventBus()){
            onEventComing(center)
        }
    }

    open  fun onEventComing(center: EventCenter<*>) {

    }

    /**
     * 是否需要注册消息监听
     */
    open fun isRegisterEventBus():Boolean {
        return false
    }
}