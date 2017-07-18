package com.kotlin.databinding.zhihu.model

import java.io.Serializable

/**
 * Created by chawei on 2017/6/20.
 */
data class RecommendModel(var list:ArrayList<RecommendData>):Serializable
data class RecommendData(var id:String,var type:String,var title:String,var publication_date:String,var image_url:String,
                         var url:String,var label_info:List<LabelInfo>)
data class LabelInfo(var label_id:String,var label_name:String,var label_type:String)