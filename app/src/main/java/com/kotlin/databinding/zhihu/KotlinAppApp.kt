package cn.kotlin.com.kotlinapp

import android.app.Application
import com.kotlin.databinding.zhihu.db.CollectDbHelper
import com.kotlin.databinding.zhihu.db.UserDbHelper
import com.lzy.okgo.OkGo
import com.lzy.okgo.cookie.store.MemoryCookieStore
import com.lzy.okgo.cookie.store.PersistentCookieStore

/**
 * Created by chawei on 2017/5/27.
 */
class KotlinAppApp : Application(){

    override fun onCreate() {
        super.onCreate()
        instance=this

        OkGo.init(this)

        OkGo.getInstance()
                .debug("kotlinapp")
                .setCertificates()
                //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
                .setCookieStore(MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
                .setCookieStore(PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效

        createDb()
    }

    fun createDb(){
        UserDbHelper.dbHelper
        CollectDbHelper.collectDbHelper
    }

    companion object{
        /**
         * 得到AnxinApp的实例
        */
        var instance =KotlinAppApp()
        private set
    }

    override fun onTerminate() {
        UserDbHelper.dbHelper.close()
        CollectDbHelper.collectDbHelper.close()
        super.onTerminate()
    }

}