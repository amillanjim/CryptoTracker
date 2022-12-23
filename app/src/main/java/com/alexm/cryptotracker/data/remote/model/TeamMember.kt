package com.alexm.cryptotracker.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName

@Parcelize
data class TeamMember(
    val id: String,
    val name: String,
    val position: String
): Parcelable