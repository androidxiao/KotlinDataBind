package com.kotlin.databinding.zhihu.utils

/**
 * Created by chawei on 2017/6/26.
 */
object StringUtils {
    /**
     * 去除字符串最后一个","
     */
    fun delEndPoint(types:String):List<String>{
        var type=types.substring(0,types.length-1)
        LogUtil.debug("type--->$type")
        val split = type.split(",")
        return split
    }
}