package com.example.myapp.data.net.api

import com.example.myapp.data.net.dto.IndexDtoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShansApi {

    @GET("item/")
    suspend fun getItemById(
        @Query("id") id: Int
    ) : Response<IndexDtoItem>

    @GET("index/")
    suspend fun getItems() : Response<List<IndexDtoItem>>

    @GET("index/")
    suspend fun getItemsBySearch(
        @Query("search") search: String
    ) : Response<List<IndexDtoItem>>

}
