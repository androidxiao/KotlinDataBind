package cn.kotlin.com.kotlinapp.utils

import android.app.Activity
import cn.kotlin.com.kotlinapp.activity.MainActivity
import com.kotlin.databinding.zhihu.utils.LogUtil
import java.util.*

/**
 * Created by chawei on 2017/5/27.
 */
object ActivityUtilK {
    var activityStack=Stack<Activity>()

    /**
     *将Activity添加到栈中
     */
    fun addActivity(activity:Activity){
        activityStack.add(activity)
    }
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity():Activity= activityStack.lastElement()

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity(){
        val activity = activityStack.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity) {
        activityStack.forEach { element ->
            if (element==activity) {
                activity.finish()
                activityStack.remove(activity)
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        LogUtil.debug("element--->"+cls.javaClass.name+"   cls.name"+cls.name)
        val iterator = activityStack.iterator()
        while (iterator.hasNext()) {
            val activity = iterator.next()
            if(activity.javaClass.name==cls.name){
                activity.finish()
                iterator.remove()
            }
        }
    }

    /**
     * 移除指定的Activity
     */
    fun removeActivity(activity: Activity) {
        val iterator = activityStack.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            if (activity == next) {
                iterator.remove()
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity(){
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }

    /**
     * 关闭除首页外的所有Activity
     */
    fun finishExceptMain(){
        for (activity in activityStack) {
            if (activity is MainActivity) {
                continue
            }else{
                activity.finish()
                activityStack.remove(activity)
            }
        }

    }

    /**
     * 删除多个activity
     */
    fun finishMoreActivity(vararg activities: Class<*>) {
        for (activity in activities) {
            finishActivity(activity)
        }
    }
}