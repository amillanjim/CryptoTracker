package com.alexm.cryptotracker.presentation.adapter

import com.alexm.cryptotracker.base.BaseListAdapter
import com.alexm.cryptotracker.data.remote.model.TeamMember
import com.alexm.cryptotracker.databinding.ItemTeamMembersBinding

class TeamMembersAdapter:
    BaseListAdapter<TeamMember, ItemTeamMembersBinding>(ItemTeamMembersBinding::inflate){

    override fun bindView(teamMember: TeamMember) {
        binding.apply {
            tvName.text = teamMember.name
            tvPosition.text = teamMember.position
        }
    }
}