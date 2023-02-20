package com.example.myapp.domain.reposiory

import com.example.myapp.domain.model.Item
import com.example.myapp.utils.RequestResult

interface ShansRepository {

    suspend fun getItems(): RequestResult<List<Item>>
    suspend fun getItemsBySearch(search: String): RequestResult<List<Item>?>
    suspend fun getItemById(id: Int): RequestResult<Item>

}
