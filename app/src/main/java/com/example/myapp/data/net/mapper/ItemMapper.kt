package com.example.myapp.data.net.mapper

import com.example.myapp.data.net.dto.Categories
import com.example.myapp.data.net.dto.Field
import com.example.myapp.data.net.dto.Flags
import com.example.myapp.data.net.dto.IndexDtoItem
import com.example.myapp.domain.model.Item
import com.example.myapp.domain.model.Categories as CategoriesDomain
import com.example.myapp.domain.model.Field as FieldDomain
import com.example.myapp.domain.model.Flags as FlagsDomain

object ItemMapper {

    fun IndexDtoItem.toDomain() = Item(
        categories = categories?.toDomain(),
        description = description,
        documentation = documentation,
        fields = fields?.map { it.toDomain() },
        id = id,
        image = image,
        name = name
    )

    fun Categories.toDomain() = CategoriesDomain(
        icon = icon,
        image = image,
        name = name
    )

    fun Field.toDomain() = FieldDomain(
        flags = flags?.toDomain(),
        group = group,
        image = image,
        name = name,
        show = show,
        type = type,
        value = value
    )

    fun Flags.toDomain() = FlagsDomain(
        html = html,
        noImage = noImage,
        noName = noName,
        noValue = noValue,
        noWrap = noWrap,
        noWrapName = noWrapName
    )
}
