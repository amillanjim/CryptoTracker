package com.alexm.cryptotracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexm.cryptotracker.common.DiffUtilCallback
import com.alexm.cryptotracker.data.remote.model.Tag
import com.alexm.cryptotracker.databinding.ItemTagBinding
import javax.inject.Inject

class TagsAdapter @Inject constructor():
    ListAdapter<Tag, TagsAdapter.TagsViewHolder>(DiffUtilCallback()){

    private var _binding: ItemTagBinding? = null
    private val binding get() = _binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsAdapter.TagsViewHolder {
        _binding = ItemTagBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false )
        return TagsViewHolder(itemView = _binding!!)
    }

    override fun onBindViewHolder(holder: TagsAdapter.TagsViewHolder, position: Int) =
        holder.bind(tag = getItem(position))

    inner class TagsViewHolder(val itemView: ItemTagBinding):
        RecyclerView.ViewHolder( itemView.root ){
        internal fun bind(tag: Tag) {
            binding?.tvTagName?.text = tag.name
        }
    }
}