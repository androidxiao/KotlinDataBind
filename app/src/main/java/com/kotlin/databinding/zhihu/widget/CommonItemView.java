package com.kotlin.databinding.zhihu.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kotlin.databinding.zhihu.R;


/**
 * '我'通用的Item
 * Created by wei on 2017/6/21.
 */
public class CommonItemView extends RelativeLayout {

    private ImageView mIvIcon;
    private TextView mTvTitle;
    private TextView mTvHint;
    private View mCutLine;
    private ImageView mIvRedDot;
    private ImageView mIvArrow;
    private Context mContext;
    public CommonItemView(Context context) {
        this(context, null);
    }

    public CommonItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initView(context);
    }

    private void initView(Context context) {
        RelativeLayout mainView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.common_item_view, this);
        mIvIcon = (ImageView) mainView.findViewById(R.id.iv_icon);
        mTvTitle = (TextView) mainView.findViewById(R.id.tv_title);
        mTvHint = (TextView) mainView.findViewById(R.id.tv_hint);
        mCutLine = mainView.findViewById(R.id.cut_line);
        mIvRedDot = (ImageView) mainView.findViewById(R.id.id_iv_red_dot);
        mIvArrow = (ImageView) mainView.findViewById(R.id.iv_arrow);
    }

    public void setIconResource(int resId) {
        mIvIcon.setVisibility(View.VISIBLE);
        mIvIcon.setBackgroundResource(resId);
    }

    public void setIcon(Drawable resId) {
        mIvIcon.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mIvIcon.setBackground(resId);
        }else{
            mIvIcon.setBackgroundDrawable(resId);
        }

    }

    public void setArrowVisible(boolean visible){
        if(visible){

        }else {
            mIvArrow.setVisibility(View.GONE);
        }
    }

    public void setTitle(String text) {
        mTvTitle.setText(text);
    }

    public void setHint(String text) {
        mTvHint.setText(text);
    }

    public void setHintColor(int color) {
        mTvHint.setTextColor(color);
    }

    public void setRedDotVisible(boolean hide) {
        if (hide) {
            mIvRedDot.setVisibility(View.GONE);
        } else {
            mIvRedDot.setVisibility(View.VISIBLE);
        }
    }

    public void hideCutLine(boolean hide) {
        if (hide) {
            mCutLine.setVisibility(View.GONE);
        } else {
            mCutLine.setVisibility(View.VISIBLE);
        }
    }

}
