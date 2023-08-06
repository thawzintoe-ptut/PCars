package com.sevenpeakssoftware.core_network

import com.sevenpeakssoftware.core.exception.NetworkException
import retrofit2.Call

fun <T> Call<T>.executeOrThrow(): T {
    val response = this.execute()
    if (response.isSuccessful.not()) {
        throw NetworkException(response.errorBody(), response.code())
    }
    return response.body() ?: throw Exception()
}
