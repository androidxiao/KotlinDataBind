package com.kotlin.databinding.zhihu.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kotlin.databinding.zhihu.R;
import com.kotlin.databinding.zhihu.utils.Px2DpUtil;

import cn.kotlin.com.kotlinapp.KotlinAppApp;


/**
 * 通用的可输入的Item
 */
public class CommonEditItemView extends RelativeLayout implements EditTextClear.TextChangeWatcher, View.OnClickListener {

    private ImageView mIvIcon;
    private TextView mTvTitle;
    private View mCutLine;
    private EditTextClear mEtcInput;
    private TextView mTvUnit;
    private TextChangeWatcher mChangeWatcher;
    private OnClickUnitListener mClickUnitListener;

    public void addTextChangeWatcher(TextChangeWatcher changeWatcher) {
        mChangeWatcher = changeWatcher;
    }

    public void setOnClickUnitListener(OnClickUnitListener clickUnitListener) {
        mClickUnitListener = clickUnitListener;
    }

    public CommonEditItemView(Context context) {
        this(context, null);
    }

    public CommonEditItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        RelativeLayout mainView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.common_edit_item_view, this);
        mIvIcon = (ImageView) mainView.findViewById(R.id.id_iv_icon);
        mTvTitle = (TextView) mainView.findViewById(R.id.tv_title);
        mEtcInput= (EditTextClear) mainView.findViewById(R.id.etc_input);
        mTvUnit = (TextView) mainView.findViewById(R.id.tv_right_unit);
        mCutLine = mainView.findViewById(R.id.cut_line);
        mEtcInput.addTextChangeWatcher(this);
    }

    public void setIvIcon(Drawable resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mIvIcon.setBackground(resId);
        }else{
            mIvIcon.setBackgroundDrawable(resId);
        }
    }

    /**
     * 限制edittext中内容的长度
     * @param length
     */
    public void setEditTextLength(int length){
        mEtcInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
    }

    /**
     * 默认是6个字符的标题
     * @param text
     */
    public void setTitle(String text) {
        mTvTitle.setText(text);
    }

    /**
     * 当标题字符数一样时使用。
     * @param text
     */
    public void setTitleChar(String text) {
        RelativeLayout.LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        lp.leftMargin = Px2DpUtil.dp2px(KotlinAppApp.Companion.getInstance(), 17);
        mTvTitle.setGravity(Gravity.CENTER);
        mTvTitle.setLayoutParams(lp);
        mTvTitle.setText(text);
    }

    /**
     * 设置标题，字符数为5的宽度
     * @param text
     */
    public void setTitle5Char(String text) {
        RelativeLayout.LayoutParams lp = new LayoutParams(Px2DpUtil.dp2px(KotlinAppApp.Companion.getInstance(), 75), LayoutParams.MATCH_PARENT);
        lp.leftMargin = Px2DpUtil.dp2px(KotlinAppApp.Companion.getInstance(), 17);
        mTvTitle.setGravity(Gravity.CENTER_VERTICAL| Gravity.LEFT);
        mTvTitle.setLayoutParams(lp);
        mTvTitle.setText(text);
    }

    /**
     * 设置标题，字符数为4的宽度
     * @param text
     */
    public void setTitle4Char(String text) {
        RelativeLayout.LayoutParams lp = new LayoutParams(Px2DpUtil.dp2px(KotlinAppApp.Companion.getInstance(), 60), LayoutParams.MATCH_PARENT);
        lp.leftMargin = Px2DpUtil.dp2px(KotlinAppApp.Companion.getInstance(), 17);
        mTvTitle.setGravity(Gravity.CENTER_VERTICAL| Gravity.LEFT);
        mTvTitle.setLayoutParams(lp);
        mTvTitle.setText(text);
    }

    public void setEditHint(String hint) {
        mEtcInput.setHint(hint);
    }

    public void setUnitText(String text) {
        mTvUnit.setText(text);
    }

    public  void setEditText(String text) {
        mEtcInput.setText(text);
    }

    public String getEditText() {
        return mEtcInput.getText().toString().trim();
    }

    public void setEditSelection(int index) {
        mEtcInput.setSelection(index);
    }

    /**
     * 设置输入类型，控制键盘
     * @param type InputType类型常量
     */
    public void setInputType(int type) {
        mEtcInput.setInputType(type);
    }

    /**
     * 设置输入框是否可编辑 false 是不可
     * @param editable
     */
    public void setEditable(boolean editable) {
        if(!editable) {
            mEtcInput.setShowClear(false);
            mEtcInput.setKeyListener(null);
        }
    }

    /**
     * 设置输入文本的长度
     * @param length
     */
    public void setEditLength(int length) {
//        mEtcInput.setMaxEms(length);   // 此方法不起作用，使用下面方法即可
        mEtcInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(length)});
    }

    /**
     * 设置edittext软键盘是否是数字键盘
     * @param isNo
     */
    public void setEditTextNo(boolean isNo){
        if(isNo){
            int inputType= InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL;
            mEtcInput.setInputType(inputType);
        }else{
            int inputType= InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL;
            mEtcInput.setInputType(inputType);
        }
    }
    public void hideCutLine(boolean hide) {
        if(hide) {
            mCutLine.setVisibility(View.GONE);
        }else{
            mCutLine.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置单位可选
     */
    public void setUnitDrawable() {
        Drawable nav_up=getResources().getDrawable(R.mipmap.text_down_arrow_icon);
        int left = Px2DpUtil.dp2px(KotlinAppApp.Companion.getInstance(), 5);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        mTvUnit.setCompoundDrawables(null, null, nav_up, null);
        mTvUnit.setCompoundDrawablePadding(left);
        mTvUnit.setTextColor(getResources().getColor(R.color.color_edittext_text));
        mTvUnit.setOnClickListener(this);
    }


    @Override
    public void beforeTextChanged(int id, CharSequence s, int start, int count, int after) {
        if(mChangeWatcher != null) {
            mChangeWatcher.beforeTextChanged(getId(), s, start, count, after);
        }
    }

    @Override
    public void onTextChanged(int id, CharSequence s, int start, int count, int after) {
        if(mChangeWatcher != null) {
            mChangeWatcher.onTextChanged(getId(), s, start, count, after);
        }
    }

    @Override
    public void afterTextChanged(int id, Editable s) {
        if(mChangeWatcher != null) {
            mChangeWatcher.afterTextChanged(getId(), s);
        }
    }

    @Override
    public void onClick(View view) {
        if(mClickUnitListener != null) {
            mClickUnitListener.onClick(getId(), view);
        }
    }

    /**
     * 监听文字变化
     */
    public interface TextChangeWatcher {
        void beforeTextChanged(int id, CharSequence s, int start, int count, int after);
        void onTextChanged(int id, CharSequence s, int start, int count, int after);
        void afterTextChanged(int id, Editable s);
    }

    /**
     * 监听单位TextView点击
     */
    public interface OnClickUnitListener {
        void onClick(int id, View view);
    }

}
