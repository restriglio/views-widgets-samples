package com.rest.testgeopagos.di

import com.rest.testgeopagos.BuildConfig.BASE_URL
import com.rest.testgeopagos.network.MercadoPagoAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {

    private val timeOutThreshold : Long = 30

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder().connectTimeout(timeOutThreshold, TimeUnit.SECONDS)
            .readTimeout(timeOutThreshold, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return client
    }

    @Provides
    @Singleton
    internal fun provideRetrofitClient(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    internal fun provideMercadoPagoApi(
        retrofit: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): MercadoPagoAPI {
        return retrofit.client(okHttpClient).build().create(MercadoPagoAPI::class.java)
    }
}