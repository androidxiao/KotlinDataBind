package eventbus.com.cn.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by chawei on 2017/5/19.
 */
//具体的语法是 fun + 类型.函数(参数)
//这断代码可以写在任意可以编译到的文件中，直的注意的是，如果写在了class内部，则是局部生效。
fun Context.toast(message: String, length:Int= Toast.LENGTH_SHORT){
    Toast.makeText(this,message,length).show()
}

fun Activity.toast(message:String,length: Int=Toast.LENGTH_SHORT)=Toast.makeText(this,message,length).show()

fun Activity.log(message: String)=Log.d("KotlinBind",message)