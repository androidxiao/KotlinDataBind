package com.kotlin.databinding.zhihu.db

import android.content.ContentValues
import com.kotlin.databinding.zhihu.model.SqlUserModel

/**
 * Created by chawei on 2017/6/22.
 */
class UserDbHelper : BaseHelper() {

    companion object{
        val dbHelper= UserDbHelper()
    }

    /**
     * 获取全部用户
     */
    fun getAllUser(): ArrayList<SqlUserModel> {
        val users = ArrayList<SqlUserModel>()
        val cursor = mSqliteDb!!.query(SqliteHelper.TABEL_NAME, null, null, null, null, null, null, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast && (cursor.getString(1)) != null) {
            var model = SqlUserModel()
            model.id = cursor.getInt(0)
            model.username = cursor.getString(1)
            model.userpwd = cursor.getString(2)
            users.add(model)
            cursor.moveToNext()
        }
        cursor.close()
        return users
    }

    /**
     * 是否有该用户
     */
    fun isHasUserInfo(userName: String): Boolean {
        val cursor = mSqliteDb!!.query(SqliteHelper.TABEL_NAME, null, "username=$userName", null, null, null, null)
        val isExists = cursor.moveToFirst()
        cursor.close()
        return isExists
    }

    /**
     * 用户名与密码是否匹配
     */
    fun userPwdIsTrue(userPwd: String, userName: String): Boolean {
        var isExists = isHasUserInfo(userName)
        if (isExists) {
            val cursor = mSqliteDb!!.rawQuery("select * from ${SqliteHelper.TABEL_NAME} where username=? and userpwd=?", arrayOf("$userName", "$userPwd"))
            val moveToFirst = cursor.moveToFirst()
            cursor.close()
            return moveToFirst
        }
        return false
    }


    /**
     * 修改密码
     */
    fun updatePwd(userName: String, updatePwd: String): Int {
        var values = ContentValues()
        values.put("userpwd", updatePwd)
        return mSqliteDb!!.update(SqliteHelper.TABEL_NAME, values, "username=$userName", null)
    }

    /**
     * 注册用户
     */
    fun registerUser(model: SqlUserModel): Long {
        if (isHasUserInfo(model.username)) {
            return -1
        }
        val values = ContentValues()
        values.put("username", model.username)
        values.put("userpwd", model.userpwd)
        var count = mSqliteDb!!.insert(SqliteHelper.TABEL_NAME, "id", values)

        return count
    }

    /**
     * 注销用户
     */
    fun delUserInfo(userName: String): Int {
        return mSqliteDb!!.delete(SqliteHelper.TABEL_NAME, "username=$userName", null)
    }


}