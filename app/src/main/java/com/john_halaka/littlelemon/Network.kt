package com.john_halaka.littlelemon

import com.john_halaka.littlelemon.data.MenuItem

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItem>
)
