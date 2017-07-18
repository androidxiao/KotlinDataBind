package cn.kotlin.com.kotlinapp.model

/**
 * Created by chawei on 2017/5/15.
 */

data class EventCenter<T>(var eventCode: Int,var data:T?=null)

//class EventCenter<T> {
//
//    var eventCode = -1
//        private set
//
//    var data: T?=null
//
//    constructor(eventCode: Int) {
//        this.eventCode = eventCode
//    }
//
//    constructor(eventCode: Int, data: T?=null) {
//        this.eventCode = eventCode
//        this.data = data
//    }
//}