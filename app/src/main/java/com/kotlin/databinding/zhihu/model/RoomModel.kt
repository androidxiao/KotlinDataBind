package com.kotlin.databinding.zhihu.model

import android.databinding.BaseObservable
import android.databinding.Observable
import android.databinding.ObservableField
import android.text.TextUtils.isEmpty
import com.kotlin.databinding.zhihu.utils.LogUtil

/**
 * Created by chawei on 2017/7/14.
 */
class RoomModel(val onBtnClickListener: (isEnable: Boolean) -> Unit) : BaseObservable() {

    var orderNo=ObservableField<String>()
    var orderPrice=ObservableField<String>()
    var checkinTime=ObservableField<String>()
    var leaveTime=ObservableField<String>()
    var duration=ObservableField<String>()
    var roomType = ObservableField<String>()
    var inAdvanceRoom=ObservableField<String>()
    var orderNoEmpty=true
    var orderPriceEmpty=true
    var checkinTimeEmpty=true
    var leaveTimeEmpty=true
    var durationEmpty=true
    var roomTypeEmpty=true
    var inAdvanceRoomEmpty=true

    fun btnIsEnable(model:RoomModel){
        isPropertyChanged(model.checkinTime)
        isPropertyChanged(model.duration)
        isPropertyChanged(model.inAdvanceRoom)
        isPropertyChanged(model.leaveTime)
        isPropertyChanged(model.orderNo)
        isPropertyChanged(model.orderPrice)
        isPropertyChanged(model.roomType)
    }

    fun isPropertyChanged(property: ObservableField<String>){
        property.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                isPropertyEmpty()
            }

        })
    }

    fun isPropertyEmpty(){
        orderNoEmpty= isEmpty(orderNo.get())
        orderPriceEmpty= isEmpty(orderPrice.get())
        checkinTimeEmpty= isEmpty(checkinTime.get())
        leaveTimeEmpty= isEmpty(leaveTime.get())
        durationEmpty= isEmpty(duration.get())
        roomTypeEmpty= isEmpty(roomType.get())
        inAdvanceRoomEmpty= isEmpty(inAdvanceRoom.get())
        if (orderNoEmpty) {
            LogUtil.debug("空空空空空")
            onBtnClickListener(false)
        }else{
            LogUtil.debug("有值------")
            onBtnClickListener(true)
        }

//        if(orderNoEmpty||orderPriceEmpty||checkinTimeEmpty||leaveTimeEmpty||durationEmpty||roomTypeEmpty||inAdvanceRoomEmpty){
//            onBtnClickListener(false)
//        }else{
//            onBtnClickListener(true)
//        }
    }


}