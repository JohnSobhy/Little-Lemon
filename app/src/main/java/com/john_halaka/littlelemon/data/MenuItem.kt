package com.john_halaka.littlelemon.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity ("menu_items")
data class MenuItem(
    @PrimaryKey
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price : String,
    @SerialName("image")
    val image : String,
    @SerialName("category")
    val category : String

)
