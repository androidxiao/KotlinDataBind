package cn.kotlin.com.kotlinapp.utils

import android.app.Activity
import android.support.v7.app.AlertDialog

/**
 * Created by chawei on 2017/5/31.
 */
class DialogUtil {

    companion object {
        val instance = DialogUtil()
    }

    fun crateDefaultDailog(activity: Activity, msg: String,   onRightClick: () -> Unit,btnStr: String="确定",title: String = "温馨提示") {
        var dialog: AlertDialog.Builder = AlertDialog.Builder(activity)
        dialog.setTitle(title)
                .setMessage(msg)
                .setNegativeButton("取消"){dialog, which ->
                    dialog.dismiss()
                }
                .setPositiveButton(btnStr) { dialog, i ->
                    onRightClick()
                    dialog.dismiss()
                }.create().show()
    }
}
