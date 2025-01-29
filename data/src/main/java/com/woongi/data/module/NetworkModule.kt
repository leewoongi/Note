package com.woongi.data.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideRetrofitService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // HttpLoggingInterceptor 생성
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // OkHttpClient에 인터셉터 추가
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

//    @Provides
//    fun provideContentService(retrofit: Retrofit) : ContentApi {
//        return retrofit.create(ContentApi::class.java)
//    }
}