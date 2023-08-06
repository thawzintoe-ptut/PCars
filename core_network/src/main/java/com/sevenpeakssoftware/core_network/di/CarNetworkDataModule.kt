package com.sevenpeakssoftware.core_network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.sevenpeakssoftware.core.exception.NetworkExceptionInterceptor
import com.sevenpeakssoftware.core_network.BuildConfig
import com.sevenpeakssoftware.core_network.CarApi
import com.sevenpeakssoftware.core_network.CarArticlesNetworkDataSourceImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarNetworkDataModule {
    private const val READ_TIME_OUT = 10L
    private const val WRITE_TIME_OUT = 10L

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext
        context: Context
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)

        val networkInterceptor = NetworkExceptionInterceptor()
        okHttpClientBuilder.addInterceptor(networkInterceptor)
        if (BuildConfig.DEBUG) {
            /**
             * showing api review UI in debugging */
            val chuckerCollector = ChuckerCollector(
                context = context,
                showNotification = true,
                retentionPeriod = RetentionManager.Period.ONE_HOUR
            )
            val chuckLoggingInterceptor = ChuckerInterceptor.Builder(context)
                .collector(chuckerCollector)
                .alwaysReadResponseBody(true)
                .build()
            okHttpClientBuilder.addInterceptor(chuckLoggingInterceptor)
        }
        okHttpClientBuilder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
        return okHttpClientBuilder
            .build()
    }

    @Provides
    @Singleton
    fun retrofit(client: OkHttpClient): Retrofit {
        val baseUrl = BuildConfig.BASE_URL

        val moshi = Moshi.Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideCarsApi(retrofit: Retrofit): CarApi {
        return retrofit.create(CarApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(
        api: CarApi
    ): com.sevenpeakssoftware.article_data.dataSource.ArticleNetworkDataSource {
        return CarArticlesNetworkDataSourceImpl(api)
    }
}
