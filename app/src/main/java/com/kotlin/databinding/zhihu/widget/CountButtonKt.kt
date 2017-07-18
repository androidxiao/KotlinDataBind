package com.kotlin.databinding.zhihu.widget

import android.content.Context
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.view.View
import android.widget.Button
import com.lzy.okgo.OkGo.init
import java.util.*
import java.util.jar.Attributes

/**
 * Created by chawei on 2017/6/23.
 */
class CountButtonKt(context: Context, attributes: Attributes, defStyleAttr:Int) :Button(context), View.OnClickListener{

    var defalutTime=60*1000
    var time=defalutTime
    var timer:Timer?=null
    var timerTask:TimerTask?=null
    var defaultText="获取验证码"
    var finishText="重新发送"

    init{
        val handler=object :Handler(){
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                text=((time/1000).toString()+"秒")
                time -=1000
                if (time < 0) {
                    isEnabled=true
                    text=finishText
                    clearTimer()
                    time=defalutTime
                }
            }
        }
    }



    fun initView(){
        if (!TextUtils.isEmpty(text)) {
            defaultText=text.toString().trim()
        }
        text=defaultText
        setOnClickListener(this)
    }

    fun initTimer(){
        timer=Timer()
        timerTask=object :TimerTask(){
            override fun run() {
                handler.sendEmptyMessage(1)
            }

        }
    }

    fun clearTimer(){
        if (timerTask != null) {
            timerTask?.cancel()
            timerTask=null
        }

        if (timer != null) {
            timer?.cancel()
            timer=null
        }
    }

    override fun onDetachedFromWindow() {
        clearTimer()
        super.onDetachedFromWindow()
    }

    fun defaultText(defaultText:String){
        this.defaultText=defaultText
    }

    fun finishText(finishText:String){
        this.finishText=finishText
    }

    fun defaultTime(defaultTime: Long) {
        this.defalutTime=defalutTime
    }


    override fun onClick(v: View?) {
        start()
    }

    fun start(){
        initTimer()
        text=((time/1000).toString()+"秒")
        isEnabled=false
        timer?.schedule(timerTask,0,1000)
    }
}