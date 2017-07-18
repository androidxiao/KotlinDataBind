package com.kotlin.databinding.zhihu.model

import android.databinding.Observable
import android.databinding.ObservableField
import android.text.TextUtils.isEmpty
import com.kotlin.databinding.zhihu.utils.PhoneNoUtils

/**
 * Created by chawei on 2017/6/23.
 */
class RegisterModel(val onBtnClickListener:(isBtnEnable:Boolean)->Unit) {
    var userPhone=ObservableField<String>()
    var userPwd = ObservableField<String>()
    var idenfityCode = ObservableField<String>()
    var isAgree = ObservableField<Boolean>()
    var isPhoneEmpty=true
    var isPwdEmpty=true
    var isCodeEmpty=true
    var isChecked=false

    fun btnIsEnable(model:RegisterModel){
        isPropertyChanged(model.userPhone)
        isPropertyChanged(model.userPwd)
        isPropertyChanged(model.idenfityCode)
        isPropertyChangedBoolean(model.isAgree)
    }

    fun isPropertyChanged(property: ObservableField<String>) {
        property.addOnPropertyChangedCallback(object :Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                isPropertyEmpty()
            }

        })
    }

    fun isPropertyChangedBoolean(property: ObservableField<Boolean>) {
        property.addOnPropertyChangedCallback(object :Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                isPropertyEmpty()
            }

        })
    }

    fun isPropertyEmpty(){
        isPhoneEmpty=if(isEmpty(userPhone.get())){
            isEmpty(userPhone.get())
        }else {
            PhoneNoUtils.isMobileNO(userPhone.get())
        }
        isPwdEmpty= isEmpty(userPwd.get())
        isCodeEmpty= isEmpty(idenfityCode.get())

        if (isAgree.get() == null) isChecked=false else isChecked=isAgree.get()

        if(!isPhoneEmpty&&!isPwdEmpty&&!isCodeEmpty&&isChecked){
            onBtnClickListener(true)
        }else{
            onBtnClickListener(false)
        }
    }

}