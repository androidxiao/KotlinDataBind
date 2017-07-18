package com.kotlin.databinding.zhihu.tools;

/**
 * Created by chawei on 2017/6/20.
 */

public class Constants {
    public static final boolean DEBUG=true;
    public static final String IS_LOGIN="isLogin";
    public static final String RECOMMEND_URL="http://qt.qq.com/php_cgi/tgp_news/php/varcache_getnews.php?id=1&version=574&op_type=2&last_timestamp=0&total_count=";
    //豆瓣基础地址
    public static final String DOUBAN_BASE_URL="https://api.douban.com/v2/book/search?tag=";
    //豆瓣开始页码
    public static final String DOUBAN_START_PAGE="&start=";
    //豆瓣每页的个数
    public static final String DOUBAN_START_COUNT="&count=";
    //固定尾部
    public static final String DOUBAN_URL_END="&fields=id,title,subtitle,origin_title,rating,author,translator,publisher,pubdate,summary,images,pages,price,binding,isbn13,series";
}
