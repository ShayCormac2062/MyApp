package com.example.myapp.data.net.dto

import kotlinx.serialization.Serializable

@Serializable
data class IndexDtoItem(
    val categories: Categories?,
    val description: String?,
    val documentation: String?,
    val fields: List<Field>?,
    val id: Int?,
    val image: String?,
    val name: String?
)
