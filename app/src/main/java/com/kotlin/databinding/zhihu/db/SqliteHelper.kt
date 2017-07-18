package com.kotlin.databinding.zhihu.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.kotlin.databinding.zhihu.utils.LogUtil

/**
 * Created by chawei on 2017/6/22.
 */
class SqliteHelper(context:Context,name:String,factory:SQLiteDatabase.CursorFactory?,version:Int) :SQLiteOpenHelper(context,name,factory,version){


    companion object{
        val TABEL_NAME="user"
        val TABLE_NAME_COLLECT_RECOM="collectrecom"
        val SQL_CREATE_USER= " create table if not exists $TABEL_NAME (id integer auto primary key, username varchar,userpwd varchar )"
        val SQL_CREATE_COLLECT_RECOM=" create table if not exists $TABLE_NAME_COLLECT_RECOM (id integer auto primary key,titleid varchar,title varchar,publication_date varchar," +
                " image_url varchar, url varchar,types varchar)"
        val SQL_DROP_USER="drop table if exists $TABEL_NAME"
        val SQL_DROP_COLLECT_RECOM="drop table if exists $TABLE_NAME_COLLECT_RECOM"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_USER)
        db.execSQL(SQL_CREATE_COLLECT_RECOM)
        LogUtil.debug("创建表成功")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DROP_USER)
        db.execSQL(SQL_DROP_COLLECT_RECOM)
        onCreate(db)
    }


}