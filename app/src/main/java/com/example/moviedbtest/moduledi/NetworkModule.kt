package com.example.moviedbtest.moduledi

import com.example.moviedbtest.BuildConfig
import com.example.moviedbtest.data.RemoteDataSource
import com.example.moviedbtest.network.MainService
import com.example.moviedbtest.util.Constants.API_TIMEOUT
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Headers.Companion.toHeaders
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        var httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClient = OkHttpClient.Builder().addInterceptor(interceptor)
        }
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val mapHeaders = HashMap<String, String>()

            var headers = original.headers
            if (headers.size > 0) {
                for (i in 0 until headers.size) {
                    mapHeaders[headers.name(i)] = headers.value(i)
                }
            }

            headers = mapHeaders.toHeaders()

            val request = original.newBuilder()
                .headers(headers)
                .build()
            chain.proceed(request)
        }

        httpClient.connectTimeout(API_TIMEOUT, TimeUnit.MINUTES)
        httpClient.readTimeout(API_TIMEOUT, TimeUnit.MINUTES)
        httpClient.connectTimeout(API_TIMEOUT, TimeUnit.MINUTES)

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): MainService {
        return retrofit.create(MainService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        mainService: MainService
    ): RemoteDataSource {
        return RemoteDataSource(mainService)
    }
}