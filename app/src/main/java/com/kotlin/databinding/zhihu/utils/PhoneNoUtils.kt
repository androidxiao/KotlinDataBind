package com.kotlin.databinding.zhihu.utils

import java.util.regex.Pattern

/**
 * Created by chawei on 2017/6/23.
 */
object PhoneNoUtils {
    /**
     * 判断手机号是否合法
     */
    fun isMobileNO(mobiles: String): Boolean {
        val p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")
        val m = p.matcher(mobiles)
        return !m.matches()
    }
}