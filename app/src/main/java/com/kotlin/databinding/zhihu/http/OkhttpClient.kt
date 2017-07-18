package com.kotlin.databinding.zhihu.http

import android.content.Context
import android.support.v4.util.ArrayMap
import com.kotlin.databinding.zhihu.utils.LogUtil
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import okhttp3.Call
import okhttp3.Response
import java.lang.Exception

/**
 * Created by chawei on 2017/6/20.
 */
object OkhttpClient {

    fun OkPost(url: String, map: ArrayMap<String, String>? = null, requestId: Int, onJsonCallback: (json: String, requestId: Int) -> Unit) {
        OkGo.post(url)
                .execute(object : StringCallback() {
                    override fun onSuccess(t: String, call: Call?, response: Response?) {
                        onJsonCallback(t, requestId)
                    }

                    override fun onCacheSuccess(t: String, call: Call?) {
                        onJsonCallback(t, requestId)
                    }

                    override fun onCacheError(call: Call?, e: Exception) {
                        super.onCacheError(call, e)
                        LogUtil.debug("获取缓存失败message--->${e.message}--cause->${e.cause}")
                    }
                })
    }


    fun OkPost(context: Context,url: String, onJsonCallback: (json: String) -> Unit,onFinishCallback:()->Unit) {
        OkGo.post(url)
                .tag(context)
                .execute(object : StringCallback() {
                    override fun onSuccess(t: String, call: Call?, response: Response?) {
                        onJsonCallback(t)
                    }

                    override fun onCacheSuccess(t: String, call: Call?) {
                        onJsonCallback(t)
                    }

                    override fun onCacheError(call: Call?, e: Exception) {
                        super.onCacheError(call, e)
                        LogUtil.debug("获取缓存失败message--->${e.message}--cause->${e.cause}")
                    }

                    override fun onAfter(t: String?, e: Exception?) {
                        super.onAfter(t, e)
                        onFinishCallback()
                    }
                })
    }

    fun cancelRequest(context: Context){
        OkGo.getInstance().cancelTag(context)
    }
}