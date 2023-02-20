package com.example.myapp.domain.model

data class Item(
    val categories: Categories?,
    val description: String?,
    val documentation: String?,
    val fields: List<Field>?,
    val id: Int?,
    val image: String?,
    val name: String?
)
