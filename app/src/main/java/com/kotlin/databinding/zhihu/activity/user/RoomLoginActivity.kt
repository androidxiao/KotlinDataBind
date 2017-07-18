package com.kotlin.databinding.zhihu.activity.user

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.RoomLoginBind
import com.kotlin.databinding.zhihu.model.Room
import com.kotlin.databinding.zhihu.model.RoomModel
import com.kotlin.databinding.zhihu.utils.LogUtil
import com.kotlin.databinding.zhihu.widget.CommonEditItemView

class RoomLoginActivity : AppCompatActivity(), CommonEditItemView.TextChangeWatcher {


    lateinit var roomBind:RoomLoginBind
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomBind=DataBindingUtil.setContentView<RoomLoginBind>(this,R.layout.activity_room_login_layout)

        var model=RoomModel{
            isEnable ->  isBtnEnable(isEnable)
        }
        var room= Room()
        room.orderNo="111111"
        model.orderNo.set("aaaaaa")
        model.btnIsEnable(model)
        roomBind.roomModel=model
        roomBind.room=room
    }

    fun isBtnEnable(isEnable:Boolean){
        LogUtil.debug("isEnable--->$isEnable")
        roomBind.idBtnSubmit.isEnabled=isEnable
    }

    fun editNotEmptyBtnEnable(){
        roomBind.idEditOrder.addTextChangeWatcher(this)
    }


    override fun beforeTextChanged(id: Int, s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(id: Int, s: CharSequence?, start: Int, count: Int, after: Int) {
        when (id) {

        }
    }

    override fun afterTextChanged(id: Int, s: Editable?) {
    }
}
