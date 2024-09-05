package com.sample.app.fetchrewardscodingexercise.di

import com.sample.app.fetchrewardscodingexercise.data.common.Constants
import com.sample.app.fetchrewardscodingexercise.data.remote.ListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //Create Retrofit to get the list from API
    @Provides
    @Singleton
    fun provideListApi(): ListApi {
        val clientSetup = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientSetup)
            .build()
            .create(ListApi::class.java)
    }
}
