package com.example.myapp.data.net.dto

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val icon: String?,
    val id: Int?,
    val image: String?,
    val name: String?
)
