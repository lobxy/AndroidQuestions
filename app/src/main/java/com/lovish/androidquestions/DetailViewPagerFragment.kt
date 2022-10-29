package com.lovish.androidquestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.lovish.androidquestions.databinding.FragmentDetailViewPagerBinding
import com.lovish.androidquestions.model.Question

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentDetailViewPagerBinding
    private lateinit var adapter: ViewPagerAdapter

    private var positionSelected: Int = 0
    private var dataList: ArrayList<Question>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            positionSelected = it.getInt(ARG_PARAM1)
            dataList = it.getParcelableArrayList(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_view_pager, container, false)
        binding.clickEvent = ClickAction()

        initViewPager()

        return binding.root
    }

    private fun initViewPager() {
        adapter = ViewPagerAdapter(childFragmentManager, dataList)
        binding.idViewPager.setCurrentItem(positionSelected, false)
        binding.idViewPager.adapter = adapter
        binding.idViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                binding.data = dataList?.get(position)
                binding.executePendingBindings()
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    companion object {
        const val TAG = "DetailViewPagerFragment"

        @JvmStatic
        fun newInstance(position: Int, dataList: ArrayList<Question>?) = DetailViewPagerFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PARAM1, position)
                putParcelableArrayList(ARG_PARAM2, dataList)
            }
        }
    }

    inner class ClickAction {
        fun backPressed(view: View) {
            activity?.onBackPressed()
        }

        fun bookmark(view: View) {
            dataList?.get(binding.idViewPager.currentItem)?.bookmarked = !(dataList?.get(binding.idViewPager.currentItem)?.bookmarked == true)
        }

    }
}