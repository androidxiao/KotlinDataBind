package cn.kotlin.com.kotlinapp.activity

import android.support.v4.app.Fragment
import android.view.View
import cn.kotlin.com.kotlinapp.activity.base.BaseActivity
import cn.kotlin.com.kotlinapp.fragment.HomeFragment
import cn.kotlin.com.kotlinapp.fragment.PersonFragment
import cn.kotlin.com.kotlinapp.fragment.ProductFragment
import cn.kotlin.com.kotlinapp.fragment.base.BaseFragment
import cn.kotlin.com.kotlinapp.utils.ActivityUtilK
import com.kotlin.databinding.zhihu.R
import kotlinx.android.synthetic.main.activity_fragment_controller_layout.*
import java.util.*

class MainActivity : BaseActivity(){


    var mFragments= ArrayList<BaseFragment>()
    var mLastFragment= Fragment()

    override fun beforeViewData() {
    }

    override fun initLayoutView() {
        setContentView(R.layout.activity_fragment_controller_layout)
        initFragmentController()
    }


    fun initFragmentController() {
        mFragments = ArrayList<BaseFragment>()
        mFragments.add(HomeFragment())
        mFragments.add(ProductFragment())
        mFragments.add(PersonFragment())
        switchFragment(0)
        rg_btn.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rb_home->switchFragment(0)
                R.id.rb_product->switchFragment(1)
                R.id.rb_person-> {
                    switchFragment(2)
                }
            }
        }

    }

    fun switchFragment(position: Int){
        var fragment=mFragments[position]
        try {
            if (fragment == null) fragment = mFragments[0]
            if (fragment.equals(mLastFragment)) {
                return
            }

            var mFragmentManager = supportFragmentManager
            var mTransaction = mFragmentManager.beginTransaction()
            if (mLastFragment != null) {
                mTransaction.hide(mLastFragment)
            }
            if (!fragment.isAdded) {
                mTransaction.add(R.id.fl_content, fragment)
            } else {
                mTransaction.show(fragment)
                fragment.onResume()
            }
            mTransaction.commitAllowingStateLoss()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            mLastFragment = fragment
        }
    }

    override fun requestNetData() {
    }

    override fun onClick(v: View?) {
    }

    override fun onBackPressed() {
        ActivityUtilK.finishAllActivity()
    }


    override fun isRegisterEventBus(): Boolean {
        return true
    }



}