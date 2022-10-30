package com.lovish.androidquestions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.lovish.androidquestions.databinding.FragmentDetailViewPagerBinding
import com.lovish.androidquestions.model.Question

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentDetailViewPagerBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var dataUpdatedCallback: IDataUpdated

    private var positionSelected: Int = 0
    private var dataList: ArrayList<Question>? = null
    private var isDataChanged: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataUpdatedCallback = context as IDataUpdated
    }

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
        binding.lastPosition = dataList?.size ?: 0

        initViewPager()

        return binding.root
    }

    private fun initViewPager() {
        val myOnPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
            //declare key
            var first = true
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (first && positionOffset == 0f && positionOffsetPixels == 0) {
                    onPageSelected(0)
                    first = false
                }
            }

            override fun onPageSelected(position: Int) {
                binding.data = dataList?.get(position)
                binding.currentPosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        }
        adapter = ViewPagerAdapter(childFragmentManager, dataList)
        binding.idViewPager.adapter = adapter
        binding.idViewPager.addOnPageChangeListener(myOnPageChangeListener)
        binding.idViewPager.setCurrentItem(positionSelected, false)
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
            if (isDataChanged) dataUpdatedCallback.dataUpdated()
            activity?.onBackPressed()
        }

        fun next(view: View) {
            binding.idViewPager.currentItem = binding.idViewPager.currentItem + 1
        }

        fun previous(view: View) {
            binding.idViewPager.currentItem = binding.idViewPager.currentItem - 1
        }

        fun bookmark(view: View) {
            isDataChanged = true
            dataList?.get(binding.idViewPager.currentItem)?.bookmarked = !(dataList?.get(binding.idViewPager.currentItem)?.bookmarked == true)
            //todo : smart refresh technique must be allotted
            binding.data = dataList?.get(binding.idViewPager.currentItem)
        }

    }
}