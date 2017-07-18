package com.kotlin.databinding.zhihu.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by chawei on 2017/6/20.
 */

object DateFormatUtilsKT {
    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @return
     *
     */
    fun stampToDate(seconds: String?): String {
        var format: String? = null
        if (seconds == null || seconds.isEmpty() || seconds == "null") {
            return ""
        }
        if (format == null || format.isEmpty()) {
            //            format = "yyyy-MM-dd HH:mm:ss";
            format = "HH:mm"
        }
        val sdf = SimpleDateFormat(format)
        return sdf.format(Date(java.lang.Long.valueOf(seconds + "000")!!))
    }

}
