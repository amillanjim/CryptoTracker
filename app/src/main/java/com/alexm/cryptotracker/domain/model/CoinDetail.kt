package com.alexm.cryptotracker.domain.model

import com.alexm.cryptotracker.data.remote.model.Tag
import com.alexm.cryptotracker.data.remote.model.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String?,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean?,
    val logo: String?,
    val tags: List<Tag>?,
    val team: List<TeamMember>?
)
