package com.sevenpeakssoftware.core_network

import com.google.common.truth.Truth.assertThat
import com.sevenpeakssoftware.core_network.model.ArticleContentResponse
import com.sevenpeakssoftware.core_network.remote.inValidArticlesResponse
import com.sevenpeakssoftware.core_network.remote.validArticlesResponse
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class CarsNetworkDataSourceImplTest {
    private lateinit var dataSource: CarArticlesNetworkDataSourceImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: CarApi

    private var exceptedTestData = listOf(ArticleContentResponse.dummy)

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(CarApi::class.java)
        dataSource = CarArticlesNetworkDataSourceImpl(api = api)
    }

    @Test
    fun inValidCarsApiResponse_returnResults() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(503)
                .setBody(inValidArticlesResponse)
        )
        val result = api.getCarArticles().execute()
        assertThat(result.isSuccessful).isFalse()
    }

    @Test
    fun validCarsApiResponse_returnResults() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validArticlesResponse)
        )
        val response = api.getCarArticles().execute()
        assertThat(response.isSuccessful).isTrue()
    }

    @Test
    fun validCarsApiResponse_returnCorrectData() = runBlocking {
        val successResponse = MockResponse().setResponseCode(200)
            .setBody(validArticlesResponse)
        mockWebServer.enqueue(successResponse)
        val response = api.getCarArticles().execute()
        mockWebServer.takeRequest()
        assertThat(response.body()!!.content).isEqualTo(exceptedTestData)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}