package cn.kotlin.com.kotlinapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.kotlin.com.kotlinapp.fragment.base.BaseFragment
import com.kotlin.databinding.zhihu.R
import com.kotlin.databinding.zhihu.fragment.BookListFragment
import kotlinx.android.synthetic.main.fragment_product_layout.*

/**
 * Created by chawei on 2017/5/29.
 */

class ProductFragment : BaseFragment() {

    val fragments = ArrayList<BaseFragment>()
    val titles = arrayListOf("热门", "新书", "小说", "科幻", "文学", "其他")

    override fun beforeViewData() {

    }

    override fun initLayout(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return View.inflate(activity, R.layout.fragment_product_layout,null)
    }

    override fun initView(view: View?, savedInstanceState: Bundle?) {
        initData()
    }

    private fun initData() {
        titles.forEach {
            fragments.add(BookListFragment.getInstance(it) as BaseFragment)
        }

        viewPager.adapter = HomePagerAdapter(fragments, titles,childFragmentManager)
        viewPager.offscreenPageLimit = 5
        //viewPager.currentItem = 2
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE)
    }


    override fun requestNetData() {


    }

    override fun onClick(v: View?) {
    }

    class HomePagerAdapter(val framents: List<BaseFragment>, val titles: ArrayList<String>, fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getItem(position: Int): Fragment = framents[position]

        override fun getCount(): Int = titles.size

        override fun getPageTitle(position: Int): CharSequence = titles[position]

    }


}
