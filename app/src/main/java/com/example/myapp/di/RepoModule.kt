package com.example.myapp.di

import com.example.myapp.data.repository.ShansRepositoryImpl
import com.example.myapp.domain.reposiory.ShansRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindShansRepository(
        shansRepository: ShansRepositoryImpl
    ): ShansRepository

}
