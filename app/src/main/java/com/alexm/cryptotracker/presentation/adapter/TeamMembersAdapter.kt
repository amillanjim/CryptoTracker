package com.alexm.cryptotracker.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexm.cryptotracker.common.DiffUtilCallback
import com.alexm.cryptotracker.data.remote.model.TeamMember
import com.alexm.cryptotracker.databinding.ItemTeamMembersBinding
import javax.inject.Inject

class TeamMembersAdapter @Inject constructor():
    ListAdapter<TeamMember, TeamMembersAdapter.TeamMembersViewHolder>(DiffUtilCallback()) {

    private var _binding: ItemTeamMembersBinding? = null
    private val binding get() = _binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMembersViewHolder {
            _binding = ItemTeamMembersBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false )
        return TeamMembersViewHolder(itemView = _binding!!)
    }

    override fun onBindViewHolder(holder: TeamMembersViewHolder, position: Int) =
        holder.bind(member = getItem(position))

    inner class TeamMembersViewHolder(val itemView: ItemTeamMembersBinding):
        RecyclerView.ViewHolder( itemView.root ){
        internal fun bind(member: TeamMember) {
            binding?.apply {
                tvName.text = member.name
                tvPosition.text = member.position
            }
        }
    }
}