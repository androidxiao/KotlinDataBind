package com.kotlin.databinding.zhihu.model

import android.databinding.Observable
import android.databinding.ObservableField
import android.text.TextUtils

/**
 * Created by chawei on 2017/6/22.
 */
class UserModel(val onBtnEnableListener: (isEnable: Boolean) -> Unit) {

    var userName = ObservableField<String>()
    var userPwd = ObservableField<String>()
    var isUserEmpty = true
    var isPwdEmpty = true

    fun btnIsEnable(model: UserModel) {
        isPropertyChanged(model.userName)
        isPropertyChanged(model.userPwd)
    }

    fun isPropertyChanged(property: ObservableField<String>) {
        property.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                isUserEmpty = TextUtils.isEmpty(userName.get())

                isPwdEmpty = TextUtils.isEmpty(userPwd.get())

                if(!isUserEmpty&&!isPwdEmpty){
                    onBtnEnableListener(true)
                }else{
                    onBtnEnableListener(false)
                }

            }

        })
    }

}