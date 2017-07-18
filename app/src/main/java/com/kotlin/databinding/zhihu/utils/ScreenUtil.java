package com.kotlin.databinding.zhihu.utils;

import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕尺寸工具类
 * Created by Adam_Lee on 2015/9/6.
 */
public class ScreenUtil {

    private static final WindowManager wm=null;
    private static final DisplayMetrics dm=null;

//    static {
//        wm = (WindowManager) KotlinAppApp.Companion.getInstance().getSystemService(Context.WINDOW_SERVICE);
//        dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//    }

    private ScreenUtil(){}

    /**
     * 屏幕宽高
     */
    public static int[] getScreenDispaly(){
        int width = dm.widthPixels;//手机屏幕的宽度
        int height = dm.heightPixels;//手机屏幕的高度
        int result[] = {width, height};
        return result;
    }

    /**
     * 屏幕宽度
     * @return 宽度
     */
    public static int getScreenWidth() {
        return dm.widthPixels;
    }

    /**
     * 屏幕高度
     * @return 高度
     */
    public static int getScreenHeight() {
        return dm.heightPixels;
    }

    /**
     * 屏幕密度
     * @return
     */
    public static float getScreenDensity() {
        return dm.density;
    }

}
