package com.zhewen.mvppackage.base

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**
* @author: zhewen
* created: 2020/5/22
* desc:
*/
class BaseFragmentAdapter : FragmentPagerAdapter {

    private var fragmentList: List<Fragment>? = ArrayList()
    private var mTitles: List<String>? = null

    //todo
    constructor(fm: FragmentManager, fragmentList: List<Fragment>) : super(fm,
        BEHAVIOR_SET_USER_VISIBLE_HINT) {
        this.fragmentList = fragmentList
    }

    constructor(fm: FragmentManager, fragmentList: List<Fragment>, mTitles: List<String>): super(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        this.mTitles = mTitles
        setFragments(fm,fragmentList,mTitles)
    }

    //刷新fragment
    @SuppressLint("CommitTransaction")
    private fun setFragments(fm: FragmentManager, fragments: List<Fragment>, mTitles: List<String>) {
        this.mTitles = mTitles
        if (this.fragmentList != null) {
            val ft = fm.beginTransaction()
            fragmentList?.forEach {
                ft.remove(it)
            }
            ft.commitAllowingStateLoss()
            fm.executePendingTransactions()
        }
        this.fragmentList = fragments
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (null != mTitles) mTitles!![position] else ""
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }

    override fun getCount(): Int {
        return fragmentList!!.size
    }

}