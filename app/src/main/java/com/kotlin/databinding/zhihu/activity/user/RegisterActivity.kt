package com.kotlin.databinding.zhihu.activity.user

import android.databinding.DataBindingUtil
import android.view.View
import cn.kotlin.com.kotlinapp.activity.base.BaseActivity
import cn.kotlin.com.kotlinapp.utils.ActivityUtilK
import cn.kotlin.com.kotlinapp.utils.SharePreUtilK
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.databinding.RegisterBind
import com.kotlin.databinding.zhihu.db.UserDbHelper
import com.kotlin.databinding.zhihu.model.RegisterModel
import com.kotlin.databinding.zhihu.model.SqlUserModel
import com.kotlin.databinding.zhihu.tools.Constants
import com.kotlin.databinding.zhihu.utils.LogUtil
import eventbus.com.cn.utils.toast
import kotlinx.android.synthetic.main.activity_register_layout.*

/**
 * Created by chawei on 2017/6/23.
 */
class RegisterActivity : BaseActivity() {

    lateinit var registBind:RegisterBind
    lateinit var registModel: RegisterModel

    override fun beforeViewData() {

    }

    override fun initLayoutView() {
        registBind = DataBindingUtil.setContentView<RegisterBind>(this, R.layout.activity_register_layout)
        registModel = RegisterModel { isBtnEnable -> isBtnEnable(isBtnEnable) }
        bindData()
    }

    fun bindData() {
        //已使用databinding方式绑定了监听
//        register_btn.setOnClickListener(this)
//        countButton.setOnClickListener(this)
        registBind.activity = this
        registBind.registerModel = registModel
        registModel.btnIsEnable(registModel)
        changeCode()
    }

    fun changeCode() {
        registBind.changeIdentifyCode.invaliChenkCode()
    }

    fun isBtnEnable(isBtnEnable: Boolean) {
        registBind.registerBtn.isEnabled = isBtnEnable
    }

    override fun requestNetData() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.register_btn -> registerUser()
            R.id.countButton -> getIdentifyCode()
        }
    }

    /**
     * 注册用户
     */
    fun registerUser() {
        LogUtil.debug("验证码是否正确---->${phone_identify_code.text.toString().trim().equals(change_identify_code.checkCode)}")
        if (phone_identify_code.text.toString().trim().equals(change_identify_code.checkCode)) {
            val model = SqlUserModel()
            model.username = user_mobile.text.toString().trim()
            model.userpwd = user_password.text.toString().trim()
            var count = UserDbHelper.dbHelper.registerUser(model)
            LogUtil.debug("userName--->${model.username}")
            LogUtil.debug("userPwd--->${model.userpwd}")
            LogUtil.debug("count----->$count")
            if (count > 0) {
                SharePreUtilK.saveBoolean(Constants.IS_LOGIN,true)
                ActivityUtilK.finishMoreActivity(LoginActivity::class.java,RegisterActivity::class.java)
            } else {
                toast("该用户名已存在，换一个吧\\(^o^)/~")
            }

            val allUser = UserDbHelper.dbHelper.getAllUser()
            for (all in allUser) {
                LogUtil.debug("all--->${all.username}")
            }
        }
    }

    /**
     * 获取验证码
     */
    fun getIdentifyCode() {
        changeCode()
    }

}