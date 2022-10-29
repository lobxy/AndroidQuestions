package com.lovish.androidquestions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lovish.androidquestions.databinding.InnerListBinding
import com.lovish.androidquestions.model.Question

class ListAdapter(private val data: ArrayList<Question>, private val itemClickCallback: ItemClicked) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: InnerListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.inner_list, parent, false
        )
        return ViewHolder(binding, itemClickCallback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(val binding: InnerListBinding, val itemClickCallback: ItemClicked) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Question, position: Int) {
            binding.position = position + 1
            binding.data = data
            binding.clickEvent = ClickAction()
        }

        inner class ClickAction {
            fun itemClicked(view: View) {
                itemClickCallback.itemClicked(adapterPosition)
            }

            fun itemBookmarked(view: View) {
                itemClickCallback.itemBookmarked(adapterPosition)
            }
        }
    }

    interface ItemClicked {
        fun itemClicked(position: Int)
        fun itemBookmarked(position: Int)
    }

}