package com.kotlin.databinding.zhihu.db

import android.content.ContentValues
import com.kotlin.databinding.zhihu.model.CollectRecomModel
import com.kotlin.databinding.zhihu.utils.LogUtil

/**
 * Created by chawei on 2017/6/26.
 */
class CollectDbHelper : BaseHelper() {
    companion object {
        val collectDbHelper = CollectDbHelper()
    }

    /**
     * 查询全部收藏数据
     */
    fun queryAllData(): ArrayList<CollectRecomModel> {
        var datas = ArrayList<CollectRecomModel>()
        val cursor = mSqliteDb!!.query(SqliteHelper.TABLE_NAME_COLLECT_RECOM, null, null, null, null, null, null, null)
        cursor.moveToFirst()
        while(!cursor.isAfterLast && (cursor.getString(1) != null)) {
            var model = CollectRecomModel(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6))
            datas.add(model)
            cursor.moveToNext()
        }
        cursor.close()
        return datas
    }

    /**
     * 收藏单条数据
     */
    fun insertCollectData(model: CollectRecomModel): Long {
        val values = ContentValues()
        values.put("titleid", model.titleId)
        values.put("title", model.title)
        values.put("publication_date", model.publication_date)
        values.put("image_url", model.image_url)
        values.put("url", model.url)
        values.put("types", model.types)
        val count = mSqliteDb!!.insert(SqliteHelper.TABLE_NAME_COLLECT_RECOM, null, values)
        return count
    }

    /**
     * 查询数据是否被收藏
     */
    fun queryCollectData(id: String): Boolean {
        val cursor = mSqliteDb!!.rawQuery("select * from ${SqliteHelper.TABLE_NAME_COLLECT_RECOM} where titleid=? ", arrayOf("$id"))
        val isExists = cursor.moveToFirst()
        cursor.close()
        LogUtil.debug("是否收藏--->$isExists")
        return isExists
    }

    /**
     * 删除单条收藏的内容
     */
    fun delCollectData(id: String): Int = mSqliteDb!!.delete(SqliteHelper.TABLE_NAME_COLLECT_RECOM, "titleid=$id", null)

    /**
     * 删除全部收藏
     */
    fun delAllCollectData(): Int = mSqliteDb!!.delete(SqliteHelper.TABLE_NAME_COLLECT_RECOM, null, null)

    /**
     * 表中是否有数据
     */
    fun isHaveContent(): Int {
        val cursor = mSqliteDb!!.rawQuery("select * from ${SqliteHelper.TABLE_NAME_COLLECT_RECOM}", null)
        return cursor.count
    }

}
