package com.kotlin.databinding.zhihu.tools;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kotlin.databinding.zhihu.R;
import com.kotlin.databinding.zhihu.utils.LogUtil;

/**
 * Created by chawei on 16/10/27.
 */

public class BindConvert {

    /**
     * 加载网络图片
     * @param imageView
     * @param imageUrl
     * @param errorId
     */
    @BindingAdapter({"imageUrl","error","isShowImage"})
    public static void loadImage(ImageView imageView,String imageUrl,Drawable errorId,boolean isShow){
        if(isShow) {
            Glide.with(imageView.getContext())
                    .load(imageUrl)
                    .error(errorId)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView);
        }else{
            LogUtil.debug("不显示大图");
            Glide.with(imageView.getContext())
                    .load(R.drawable.ic_no_pic)
                    .centerCrop()
                    .into(imageView);
        }
    }

    /**
     * 加载本地图片
     * @param view
     * @param resId
     */
    @BindingAdapter({"android:src"})
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }

}
