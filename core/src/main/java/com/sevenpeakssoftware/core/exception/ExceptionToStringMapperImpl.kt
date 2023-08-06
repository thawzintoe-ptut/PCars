package com.sevenpeakssoftware.core.exception

import android.content.Context
import com.sevenpeakssoftware.core.R
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ExceptionToStringMapperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ExceptionToStringMapper {

    companion object {
        private const val ERROR_CODE_304 = 304
        private const val ERROR_CODE_400 = 400
        private const val ERROR_CODE_401 = 401
        private const val ERROR_CODE_422 = 422
        private const val ERROR_CODE_403 = 403
        private const val ERROR_CODE_404 = 404
        private const val ERROR_CODE_500 = 500
    }

    fun exceptionNoData(): String = context.getString(R.string.error_no_data)

    override fun map(item: Throwable): String {
        return when (item) {
            is UnknownHostException -> context.getString(R.string.error_no_internet)
            is SocketTimeoutException -> context.getString(R.string.error_socket_timeout)
            is ConnectException -> context.getString(R.string.error_no_internet)
            is NetworkException -> parseNetworkError(item)
            is SyncDataNotSuccessException -> context.getString(R.string.error_not_success)
            else -> context.getString(R.string.error_generic)
        }
    }

    private fun parseNetworkError(exception: NetworkException): String {
        val exceptionString = when (exception.errorCode) {
            ERROR_CODE_304 -> context.getString(R.string.error_server_304)
            ERROR_CODE_400 -> return exception.errorBody?.let { parseMessageFromErrorBody(it.string()) }
                ?: context.getString(
                    R.string.error_generic
                )
            ERROR_CODE_401 -> return exception.errorBody?.let { parseMessageFromErrorBody(it.string()) }
                ?: context.getString(
                    R.string.error_generic
                )
            ERROR_CODE_422 -> return exception.errorBody?.let { parseMessageFromErrorBody(it.string()) }
                ?: context.getString(
                    R.string.error_generic
                )
            ERROR_CODE_403 -> return exception.errorBody?.let { parseMessageFromErrorBody(it.string()) }
                ?: context.getString(
                    R.string.error_generic
                )
            ERROR_CODE_404 -> return context.getString(R.string.error_server_404)
            ERROR_CODE_500 -> return context.getString(R.string.error_server_500)
            else -> context.getString(R.string.error_generic)
        }

        return exceptionString
    }

    private fun parseMessageFromErrorBody(errorBody: String): String {
        try {
            val errorBodyJson = JSONObject(errorBody)
            return errorBodyJson.getString("message")
        } catch (exception: JSONException) {
            Timber.e(exception)
        }

        return context.getString(R.string.error_generic)
    }
}
