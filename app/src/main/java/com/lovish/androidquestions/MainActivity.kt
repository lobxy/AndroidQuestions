package com.lovish.androidquestions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.lovish.androidquestions.databinding.ActivityMainBinding
import com.lovish.androidquestions.model.Question

class MainActivity : AppCompatActivity(), ListAdapter.ItemClicked {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataList: ArrayList<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initData()
    }

    private fun initData() {
        dataList = ArrayList()
        dataList.add(Question("question 1", "Answer 1"))
        dataList.add(Question("question 2", "Answer 2"))
        dataList.add(Question("question 3", "Answer 3"))
        dataList.add(Question("question 4", "Answer 4"))
        dataList.add(Question("question 5", "Answer 5"))
        dataList.add(Question("question 6", "Answer 6"))

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.idRecyclerView.setHasFixedSize(true)
        binding.idRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.idRecyclerView.adapter = ListAdapter(dataList, this)
    }

    override fun itemClicked(position: Int) {
        supportFragmentManager.beginTransaction().add(R.id.idFragmentContainer, DetailViewPagerFragment.newInstance(position, dataList), DetailViewPagerFragment.TAG).addToBackStack("").commit()
    }
}