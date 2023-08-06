package com.sevenpeakssoftware.core_network

import com.sevenpeakssoftware.core_network.model.GetArticlesResponse
import retrofit2.Call
import retrofit2.http.GET

interface CarApi {
    @GET("/carlist")
    fun getCarArticles(): Call<GetArticlesResponse>
}
