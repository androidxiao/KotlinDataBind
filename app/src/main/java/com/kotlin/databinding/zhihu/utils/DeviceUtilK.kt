package cn.kotlin.com.kotlinapp.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.telephony.TelephonyManager
import android.text.TextUtils

/**
 * Created by chawei on 2017/5/27.
 */
object  DeviceUtilK {
    private val NETWORKTYPE_INVALID = 0
    private val NETWORKTYPE_WAP = 1
    private val NETWORKTYPE_2G = 2
    private val NETWORKTYPE_3G = 3
    private val NETWORKTYPE_WIFI = 4
    private var mNetWorkType: Int = 0
    private var netWorkType: String? = null

    /**
     * 判断网络是否可用
     * @return
     */
    fun isNetworkAvailable(context: Context?): Boolean {
        if (context != null) {
            val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 得到当前网络环境的类型
     * @param context
     * *
     * @return
     */
    fun getNetWorkType(context: Context): String {
        val manager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            val type = networkInfo.typeName
            if (type.equals("WIFI", ignoreCase = true)) {
                mNetWorkType = NETWORKTYPE_WIFI
            } else if (type.equals("MOBILE", ignoreCase = true)) {
                val proxyHost = android.net.Proxy.getDefaultHost()
                mNetWorkType = if (TextUtils.isEmpty(proxyHost))
                    if (isFastMobileNetwork(context))
                        NETWORKTYPE_3G
                    else
                        NETWORKTYPE_2G
                else
                    NETWORKTYPE_WAP
            }
        } else {
            mNetWorkType = NETWORKTYPE_INVALID
        }
        if (mNetWorkType == 1) {
            netWorkType = "WAP"
        } else if (mNetWorkType == 2) {
            netWorkType = "2G"
        } else if (mNetWorkType == 3) {
            netWorkType = "3G"
        } else if (mNetWorkType == 4) {
            netWorkType = "WIFI"
        } else {
            netWorkType = "INVALID"
        }

        return netWorkType!!
    }

    private fun isFastMobileNetwork(context: Context): Boolean {
        val telephonyManager = context
                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        when (telephonyManager.networkType) {
            TelephonyManager.NETWORK_TYPE_1xRTT -> return false
            TelephonyManager.NETWORK_TYPE_CDMA -> return false
            TelephonyManager.NETWORK_TYPE_EDGE -> return false
            TelephonyManager.NETWORK_TYPE_EVDO_0 -> return true
            TelephonyManager.NETWORK_TYPE_EVDO_A -> return true
            TelephonyManager.NETWORK_TYPE_GPRS -> return false
            TelephonyManager.NETWORK_TYPE_HSDPA -> return true
            TelephonyManager.NETWORK_TYPE_HSPA -> return true
            TelephonyManager.NETWORK_TYPE_HSUPA -> return true
            TelephonyManager.NETWORK_TYPE_UMTS -> return true
            TelephonyManager.NETWORK_TYPE_EHRPD -> return true
            TelephonyManager.NETWORK_TYPE_EVDO_B -> return true
            TelephonyManager.NETWORK_TYPE_HSPAP -> return true
            TelephonyManager.NETWORK_TYPE_IDEN -> return false
            TelephonyManager.NETWORK_TYPE_LTE -> return true
            TelephonyManager.NETWORK_TYPE_UNKNOWN -> return false
            else -> return false
        }
    }

    /**
     * 获取application中指定的meta-data
     * @return 如果没有获取成功(没有对应值,或者异常),则返回值为空
     */
    fun getAppMetaData(mContext: Context?, key: String): String {
        var resultData:String?=null
        if (mContext == null || TextUtils.isEmpty(key)) {
            return resultData.toString()
        }
        try {
            val packageManager = mContext.packageManager
            if (packageManager != null) {
                val applicationInfo = packageManager
                        .getApplicationInfo(mContext.packageName,
                                PackageManager.GET_META_DATA)
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString(key)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return resultData.toString()
    }

    /**
     * 调用系统拨号页面
     * @param phone
     */
    fun dial(context: Context, phone: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:" + phone)
        if (null != intent.resolveActivity(context.packageManager)) {
            context.startActivity(intent)
        }
    }

    /**
     * 　　* 获取版本号
     * 　　* @return 当前应用的版本号

     */
    fun getVersion(context: Context): String {
        val manager = context.packageManager
        var info: PackageInfo? = null
        try {
            info = manager.getPackageInfo(context.packageName, 0)
            val version = info!!.versionName
            return version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return "1.0"
        }

    }
}