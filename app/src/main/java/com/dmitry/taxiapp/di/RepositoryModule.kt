package com.dmitry.taxiapp.di


import com.dmitry.taxiapp.data.repository.TaxiRepository
import com.dmitry.taxiapp.data.repository.TaxiRepositoryImpl
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
    abstract fun bindRepository(
        repositoryImpl: TaxiRepositoryImpl
    ): TaxiRepository
}