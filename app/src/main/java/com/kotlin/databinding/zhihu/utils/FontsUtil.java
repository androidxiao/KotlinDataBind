package com.kotlin.databinding.zhihu.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;

/**
 * 字体工具类
 * Created by Adam_Lee on 2015/9/2.
 */
public class FontsUtil {

    private FontsUtil() {}

    /**
     * 加载Assets文件夹里的字体
     * @param context
     * @return
     */
    public static Typeface getFont(Context context) {
        return  Typeface.createFromAsset(context.getAssets(), "fonts/bariol_regular.otf");
    }

    /**
     * 设置字体大小
     * @param text
     * @param sp
     * @param index
     * @return
     */
    public static Spannable setFontSize(String text, int sp, int index) {
        Spannable span = new SpannableString(text);
        span.setSpan(new AbsoluteSizeSpan(sp, true),
                text.length() -index, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

}
