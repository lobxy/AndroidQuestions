package com.lovish.androidquestions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lovish.androidquestions.model.Question

class ViewPagerAdapter(fragmentManager: FragmentManager, val dataList: ArrayList<Question>?) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return dataList?.size ?: 0
    }

    override fun getItem(position: Int): Fragment {
        return QuestionDetailFragment.newInstance(dataList?.get(position))
    }

}