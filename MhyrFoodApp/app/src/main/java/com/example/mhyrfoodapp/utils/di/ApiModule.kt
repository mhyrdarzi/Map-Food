package com.example.mhyrfoodapp.utils.di

import com.example.mhyrfoodapp.data.server.ApiServices
import com.example.mhyrfoodapp.utils.Constants
import com.example.mhyrfoodapp.utils.Constants.BASE_URL
import com.example.mhyrfoodapp.utils.Constants.CNN_TIMEOUT
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideBaseURl() = BASE_URL

    @Provides
    @Singleton
    fun provideConnectionTimeOut() = CNN_TIMEOUT

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideClient(time: Long, body: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(body)
        .readTimeout(time, TimeUnit.SECONDS)
        .connectTimeout(time, TimeUnit.SECONDS)
        .writeTimeout(time, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): ApiServices =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(ApiServices::class.java)
}