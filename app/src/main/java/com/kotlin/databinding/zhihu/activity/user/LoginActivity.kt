package com.kotlin.databinding.zhihu.activity.user

import android.content.Intent
import android.databinding.DataBindingUtil
import android.view.View
import cn.kotlin.com.kotlinapp.activity.base.BaseActivity
import cn.kotlin.com.kotlinapp.utils.SharePreUtilK
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.databinding.LoginBind
import com.kotlin.databinding.zhihu.db.UserDbHelper
import com.kotlin.databinding.zhihu.model.UserModel
import com.kotlin.databinding.zhihu.tools.Constants
import eventbus.com.cn.utils.toast
import kotlinx.android.synthetic.main.activity_login_layout.*
import kotlinx.android.synthetic.main.title_header_layout.*

/**
 * Created by chawei on 2017/6/22.
 */
class LoginActivity : BaseActivity(){

    var loginBind: LoginBind?=null
    var userModel: UserModel?=null
    override fun beforeViewData() {

    }

    override fun initLayoutView() {

        loginBind= DataBindingUtil.setContentView<LoginBind>(this, R.layout.activity_login_layout)

        userModel= UserModel {
            isEnable->   btnIsEnable(isEnable)
        }

        bindData()

        bindListener()
    }

    fun bindData(){
        loginBind?.activity=this
        loginBind?.userModel=userModel
        userModel?.btnIsEnable(userModel!!)
    }

    fun btnIsEnable(isEnable:Boolean){
        loginBind!!.loginBtn.isEnabled = isEnable
    }

    fun bindListener(){
        title_left_btn.setOnClickListener(this)
    }

    override fun requestNetData() {
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.login_btn->login()
            R.id.register_btn->register()
            R.id.title_left_btn->finish()
        }
    }

    fun login(){
        var isUserExists= UserDbHelper.dbHelper.isHasUserInfo(user_mobile.text.toString())
        if(!isUserExists){
            toast("您还没有注册哦，快去注册吧\\(^o^)/~")
            return
        }
        val userPwdIsTrue = UserDbHelper.dbHelper.userPwdIsTrue(user_mobile.text.toString(), user_password.text.toString())
        if(userPwdIsTrue){
            SharePreUtilK.saveBoolean(Constants.IS_LOGIN,userPwdIsTrue)
        }
    }
    fun register() = startActivity(Intent(this,RegisterActivity::class.java))
}