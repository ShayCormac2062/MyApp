package com.example.myapp.domain.usecase

import com.example.myapp.domain.reposiory.ShansRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetItemByIdUseCase @Inject constructor(
    private val repository: ShansRepository,
    @Named("io") private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: Int) =
        withContext(dispatcher) {
            repository.getItemById(id)
        }

}
