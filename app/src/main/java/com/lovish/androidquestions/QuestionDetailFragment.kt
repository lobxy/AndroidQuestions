package com.lovish.androidquestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lovish.androidquestions.databinding.FragmentQuestionDetailBinding
import com.lovish.androidquestions.model.Question

private const val ARG_PARAM1 = "param1"

class QuestionDetailFragment : Fragment() {

    private lateinit var binding: FragmentQuestionDetailBinding
    private var data: Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question_detail, container, false)
        binding.data = data
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Question?) = QuestionDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_PARAM1, param1)
            }
        }
    }
}