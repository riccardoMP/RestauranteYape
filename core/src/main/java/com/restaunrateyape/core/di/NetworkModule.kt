package com.restaunrateyape.core.di

import com.restaunrateyape.core.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    companion object {
        private const val API_BASE_URL = "https://demo4178301.mockable.io/"
        private const val REST_TIMEOUT: Long = 20
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(REST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(REST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REST_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}
