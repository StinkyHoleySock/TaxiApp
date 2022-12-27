package com.dmitry.taxiapp.di


import com.dmitry.taxiapp.data.api.TaxiApi
import com.dmitry.taxiapp.data.repository.TaxiRepositoryImpl
import com.dmitry.taxiapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): TaxiApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaxiApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(service: TaxiApi): TaxiRepositoryImpl {
        return TaxiRepositoryImpl(service)
    }
}