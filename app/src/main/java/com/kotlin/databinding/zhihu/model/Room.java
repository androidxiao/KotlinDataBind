package com.kotlin.databinding.zhihu.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.kotlin.databinding.zhihu.BR;

/**
 * Created by chawei on 2017/7/14.
 */

public class Room extends BaseObservable{

    private String orderNo;
    private String orderPrice;

    @Bindable
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        notifyPropertyChanged(BR.orderNo);
    }

    @Bindable
    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
        notifyPropertyChanged(BR.orderPrice);
    }
}
