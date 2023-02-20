package com.example.myapp.di

import com.example.myapp.data.net.api.ShansApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideAilmentsApi(
        @Named("retrofit") retrofit: Retrofit
    ): ShansApi = retrofit.create(ShansApi::class.java)


}
