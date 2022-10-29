package com.lovish.androidquestions

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.lovish.androidquestions.databinding.ActivityMainBinding
import com.lovish.androidquestions.model.Question

class MainActivity : AppCompatActivity(), ListAdapter.ItemClicked {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataList: ArrayList<Question>
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initData()
    }

    private fun initData() {
        dataList = ArrayList()
        dataList.add(Question("question 1", "Answer 1", false))
        dataList.add(Question("question 2", "Answer 2", false))
        dataList.add(Question("question 3", "Answer 3", false))
        dataList.add(Question("question 4", "Answer 4", false))
        dataList.add(Question("question 5", "Answer 5", false))
        dataList.add(Question("question 6", "Answer 6", false))

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = ListAdapter(dataList, this)
        binding.idRecyclerView.setHasFixedSize(true)
        binding.idRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.idRecyclerView.adapter = adapter
    }

    override fun itemClicked(position: Int) {
        supportFragmentManager.beginTransaction().add(R.id.idFragmentContainer, DetailViewPagerFragment.newInstance(position, dataList), DetailViewPagerFragment.TAG).addToBackStack("").commit()
    }

    override fun itemBookmarked(position: Int) {
        dataList[position].bookmarked = !dataList[position].bookmarked
        adapter.notifyItemChanged(position)
    }

    inner class ClickAction {
        fun back(view: View) {
            finish()
        }

        fun search() {
            binding.idToolbar.visibility = View.GONE
            binding.idSearchToolbar.visibility = View.VISIBLE
        }
    }
}