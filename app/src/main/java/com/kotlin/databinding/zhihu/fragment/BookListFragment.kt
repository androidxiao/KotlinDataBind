package com.kotlin.databinding.zhihu.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.haohao.dbbook.presentation.adapter.BookListAdapter
import cn.kotlin.com.kotlinapp.fragment.base.BaseFragment
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.http.OkhttpClient
import com.kotlin.databinding.zhihu.model.BookInfoResponse
import com.kotlin.databinding.zhihu.model.BookModel
import com.kotlin.databinding.zhihu.tools.Constants
import com.kotlin.databinding.zhihu.tools.JsonParse.parseJson
import com.kotlin.databinding.zhihu.utils.LogUtil
import kotlinx.android.synthetic.main.fragment_list_layout.*

/**
 * Created by chawei on 2017/6/28.
 */
class BookListFragment : BaseFragment(), XRecyclerView.LoadingListener {

    companion object {
        val FIELDS = "id,title,subtitle,origin_title,rating,author,translator,publisher,pubdate,summary,images,pages,price,binding,isbn13,series"
        val KEY_TAG = "key_tag"
        val SPAN_COUNT = 1
        val TAG = "BookListFragment"

        fun getInstance(tag: String): Fragment {
            val args = Bundle()
            args.putString(KEY_TAG, tag)
            val fragment = BookListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var page = 1
    private var count = 20
    private var bookTag = "hot"
    //用来判断load了多少次
    private var loadCount=1
    private var books = ArrayList<BookInfoResponse>()
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var bookListAdapter: BookListAdapter
    private var isRefresh = false
    private var isLoad = false

    override fun beforeViewData() {
    }

    override fun initLayout(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bookTag = arguments.getString(KEY_TAG)
        LogUtil.debug("bookTag----->$bookTag")
        return View.inflate(activity, R.layout.fragment_list_layout, null)
    }

    override fun initView(rootView: View?, savedInstanceState: Bundle?) {
        bindView()
        requestNetData()
    }

    fun bindView() {
        gridLayoutManager = GridLayoutManager(context, SPAN_COUNT)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return bookListAdapter.getItemColumnSpan(position)
            }
        }

        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = gridLayoutManager

        //adapter
        bookListAdapter = BookListAdapter(books, SPAN_COUNT) {
            view: View, i: Int ->
            navigateToDetail(view, i)
        }
        recyclerView.adapter = bookListAdapter

        recyclerView.setPullRefreshEnabled(true)
        recyclerView.setLoadingMoreEnabled(true)
        recyclerView.setLoadingListener(this)
    }

    fun navigateToDetail(view: View, position: Int) {

    }

    override fun requestNetData() {
        var url = Constants.DOUBAN_BASE_URL + bookTag + Constants.DOUBAN_START_PAGE + page + Constants.DOUBAN_START_COUNT + count + Constants.DOUBAN_URL_END
        LogUtil.debug("url----->$url")
        OkhttpClient.OkPost(activity,url, {
            json ->
            parseData(json)
        }, {

            if (isRefresh) {
                recyclerView.refreshComplete()
                isRefresh = false
            } else if (isLoad) {
                recyclerView.loadMoreComplete()
                isLoad = false
            }
            //只允许上拉3次
            if(loadCount==3){
                recyclerView.noMoreLoading()
            }
        })
    }

    fun parseData(json: String) {
        val model = parseJson(json, BookModel::class.java)
        books = model.books
        if (page == 0) {
            bookListAdapter.onRefreshData(books)
        } else {
            bookListAdapter.onLoadMoreData(books)
        }
    }

    override fun onClick(v: View?) {
    }


    override fun onRefresh() {
        page = 1
        loadCount=1
        count = 20
        isRefresh = true
        requestNetData()
    }


    override fun onLoadMore() {
        isLoad = true
        ++loadCount
        page *= count
        requestNetData()
    }

    override fun onDestroyView() {
        OkhttpClient.cancelRequest(activity)
        super.onDestroyView()
    }
}