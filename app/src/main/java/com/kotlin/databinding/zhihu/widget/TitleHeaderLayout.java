package com.kotlin.databinding.zhihu.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kotlin.databinding.zhihu.R;

/**
 * Created by chawei on 2017/6/27.
 */

public class TitleHeaderLayout extends LinearLayout {

    private ImageView mIvBack;
    private TextView mTvTitle;
    private TextView mTvRight;
    public TitleHeaderLayout(Context context) {
        this(context,null);
    }

    public TitleHeaderLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleHeaderLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        View view=LayoutInflater.from(getContext()).inflate(R.layout.title_header_layout,this);
        mIvBack = (ImageView) view.findViewById(R.id.title_left_btn);
        mTvTitle = (TextView) view.findViewById(R.id.title_middle_text);
        mTvRight = (TextView) view.findViewById(R.id.title_right_btn);
    }

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setRightTitle(String title) {
        mTvRight.setText(title);
    }

}
