package com.alexm.cryptotracker.presentation.adapter

import com.alexm.cryptotracker.base.BaseListAdapter
import com.alexm.cryptotracker.data.remote.model.Tag
import com.alexm.cryptotracker.databinding.ItemTagBinding

class TagsAdapter: BaseListAdapter<Tag, ItemTagBinding>(ItemTagBinding::inflate) {
    override fun bindView(tag: Tag) {
        binding.tvTagName.text = tag.name
    }
}