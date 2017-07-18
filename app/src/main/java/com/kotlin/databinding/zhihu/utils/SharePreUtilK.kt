package cn.kotlin.com.kotlinapp.utils

import android.content.Context
import android.content.SharedPreferences
import cn.kotlin.com.kotlinapp.KotlinAppApp

/**
 * Created by chawei on 2017/5/27.
 */
object SharePreUtilK {
    var mSharePre: SharedPreferences = KotlinAppApp.instance.getSharedPreferences("Global", Context.MODE_PRIVATE)
    var mEditor: SharedPreferences.Editor

    init {
        mEditor = mSharePre.edit()
    }

    fun saveString(name: String, value: String) {
        mEditor.putString(name,value)
        mEditor.commit()
    }

//    @JvmOverloads convert java to kotlin is hava ,注释看看
    fun getStringValue(name: String): String = mSharePre.getString(name,"")

    fun saveBoolean(name: String, value: Boolean) {
        mEditor.putBoolean(name,value)
        mEditor.commit()
    }

    fun getBooleanValue(name: String)= mSharePre.getBoolean(name,false)

    fun saveInt(name: String,value:Int){
        mEditor.putInt(name,value)
        mEditor.commit()
    }

    fun getIntValue(name: String)= mSharePre.getInt(name,-1)

    fun saveFloat(name: String,value:Float){
        mEditor.putFloat(name,value)
        mEditor.commit()
    }

    fun getFloatValue(name: String)= mSharePre.getFloat(name,0f)

    fun removeValue(name: String){
        mEditor.remove(name)
        mEditor.commit()
    }

    fun removeAll() {
        mEditor.clear()
        mEditor.commit()
    }
}