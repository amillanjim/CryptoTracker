package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class CoinDetailDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("rank") val rank: Int,
    @SerialName("logo") val logo: String,
    @SerialName("is_new") val isNew: Boolean,
    @SerialName("is_active") val isActive: Boolean,
    @SerialName("description") val description: String?,
    @SerialName("message") val message: String?,
    @SerialName("open_source") val openSource: Boolean?,
    @SerialName("started_at") val startedAt: String?,
    @SerialName("development_status") val developmentStatus: String?,
    @SerialName("hardware_wallet") val hardwareWallet: Boolean?,
    @SerialName("proof_type") val proofType: String?,
    @SerialName("org_structure") val organizationStructure: String?,
    @SerialName("hash_algorithm") val algorithm: String?,
    @SerialName("contracts") val contracts: List<Contract>?,
    @SerialName("tags") val tags: List<Tag>?,
    @SerialName("type") val type: String,
    @SerialName("team") val team: List<TeamMember>?,
    @SerialName("links") val links: Links?,
    @SerialName("links_extended") val linksExtended: List<LinksExtended>?,
    @SerialName("whitepaper") val whitepaper: Whitepaper?,
    @SerialName("first_data_at") val firstDataUpdate: String?,
    @SerialName("last_data_at") val lastDataUpdate: String?
): Parcelable