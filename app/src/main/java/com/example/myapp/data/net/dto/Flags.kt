package com.example.myapp.data.net.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Flags(
    val html: Int?,
    @SerialName("no_image")
    val noImage: Int?,
    @SerialName("no_name")
    val noName: Int?,
    @SerialName("no_value")
    val noValue: Int?,
    @SerialName("no_wrap")
    val noWrap: Int?,
    @SerialName("no_wrap_name")
    val noWrapName: Int?
)
