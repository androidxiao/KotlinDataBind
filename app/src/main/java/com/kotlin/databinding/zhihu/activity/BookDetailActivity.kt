package com.kotlin.databinding.zhihu.activity

import android.view.View
import cn.kotlin.com.kotlinapp.activity.base.BaseActivity
import com.kotlin.databinding.zhihu.R

/**
 * Created by chawei on 2017/6/28.
 */
class BookDetailActivity :BaseActivity(){
    override fun beforeViewData() {

    }

    override fun initLayoutView() {
        setContentView(R.layout.activity_book_detail_layout)
    }

    override fun requestNetData() {
    }

    override fun onClick(v: View?) {
    }
}