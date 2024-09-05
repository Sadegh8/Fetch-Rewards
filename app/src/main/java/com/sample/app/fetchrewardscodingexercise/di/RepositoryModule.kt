package com.sample.app.fetchrewardscodingexercise.di

import com.sample.app.fetchrewardscodingexercise.data.repository.ListRepositoryImpl
import com.sample.app.fetchrewardscodingexercise.domain.repository.ListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsListRepository(
        breedsRepositoryImpl: ListRepositoryImpl
    ): ListRepository
}
