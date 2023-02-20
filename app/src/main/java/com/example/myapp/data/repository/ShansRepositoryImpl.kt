package com.example.myapp.data.repository

import com.example.myapp.data.net.api.ShansApi
import com.example.myapp.data.net.mapper.ItemMapper.toDomain
import com.example.myapp.domain.model.Item
import com.example.myapp.domain.reposiory.ShansRepository
import com.example.myapp.utils.ApplicationException
import com.example.myapp.utils.RequestResult
import javax.inject.Inject

class ShansRepositoryImpl @Inject constructor(
    private val api: ShansApi
) : ShansRepository {

    override suspend fun getItems(): RequestResult<List<Item>> = try {
        with(api.getItems()) {
            if (isSuccessful && body() != null) {
                RequestResult.Success(
                    data = body()?.map { it.toDomain() }
                )
            } else RequestResult.Error(
                message = ApplicationException.ApiException()
            )
        }
    } catch (ex: Throwable) {
        RequestResult.Error(
            message = ApplicationException.InternetException()
        )
    }

    override suspend fun getItemsBySearch(search: String): RequestResult<List<Item>?> = try {
        with(api.getItemsBySearch(search)) {
            if (isSuccessful && body() != null) {
                RequestResult.Success(
                    data = body()?.map {
                        it.toDomain()
                    }
                )
            } else RequestResult.Error(
                message = ApplicationException.ApiException(),
                emptyList()
            )
        }
    } catch (ex: Throwable) {
        RequestResult.Error(
            message = ApplicationException.InternetException(),
        )
    }

    override suspend fun getItemById(id: Int): RequestResult<Item> = try {
        with(api.getItemById(id)) {
            if (isSuccessful && body() != null) {
                RequestResult.Success(
                    data = body()?.toDomain()
                )
            } else RequestResult.Error(
                message = ApplicationException.ApiException()
            )
        }
    } catch (ex: Throwable) {
        RequestResult.Error(
            message = ApplicationException.InternetException(),
        )
    }
}
