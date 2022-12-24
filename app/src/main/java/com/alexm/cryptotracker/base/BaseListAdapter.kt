package com.alexm.cryptotracker.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.alexm.cryptotracker.common.DiffUtilCallback

abstract class BaseListAdapter<T: Any, VB: ViewBinding>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> VB
): ListAdapter<T, BaseListAdapter<T, VB>.BaseViewHolder>(DiffUtilCallback()) {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewTYpe: Int): BaseViewHolder {
        _binding = inflateMethod.invoke(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return BaseViewHolder(_binding!!.root)
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder, position: Int) =
        viewHolder.bind(getItem(position))

    inner class BaseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal fun bind(internalItem: T){
            bindView(item = internalItem)
        }
    }

    open fun bindView(item: T){}
}