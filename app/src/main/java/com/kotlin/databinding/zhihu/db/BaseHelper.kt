package com.kotlin.databinding.zhihu.db

import android.database.sqlite.SQLiteDatabase
import cn.kotlin.com.kotlinapp.KotlinAppApp

/**
 * Created by chawei on 2017/6/26.
 */
abstract class BaseHelper {

    var mSqliteHelper: SqliteHelper?=null
    var mSqliteDb: SQLiteDatabase?=null
    val mContext= KotlinAppApp.instance

    companion object {
        val DB_NAME = "user.db"
        val DB_VERSION = 1
    }

    init {
        mSqliteHelper = SqliteHelper(mContext, DB_NAME, null, DB_VERSION)
        mSqliteDb = mSqliteHelper!!.writableDatabase
    }


    fun close(){
        mSqliteDb!!.close()
        mSqliteHelper!!.close()
        mSqliteDb=null
        mSqliteHelper=null
    }

}