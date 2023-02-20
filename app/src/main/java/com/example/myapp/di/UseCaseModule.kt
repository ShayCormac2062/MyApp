package com.example.myapp.di

import com.example.myapp.domain.reposiory.ShansRepository
import com.example.myapp.domain.usecase.GetItemByIdUseCase
import com.example.myapp.domain.usecase.GetItemsBySearchUseCase
import com.example.myapp.domain.usecase.GetItemsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun bindGetItemByIdUseCase(
        repository: ShansRepository,
        @Named("io") dispatcher: CoroutineDispatcher
    ): GetItemByIdUseCase = GetItemByIdUseCase(
        repository,
        dispatcher
    )

    @Provides
    fun bindGetItemsBySearchUseCase(
        repository: ShansRepository,
        @Named("io") dispatcher: CoroutineDispatcher
    ): GetItemsBySearchUseCase = GetItemsBySearchUseCase(
        repository,
        dispatcher
    )

    @Provides
    fun bindGetItemsUseCase(
        repository: ShansRepository,
        @Named("io") dispatcher: CoroutineDispatcher
    ): GetItemsUseCase = GetItemsUseCase(
        repository,
        dispatcher
    )

}
