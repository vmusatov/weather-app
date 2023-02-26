package com.weatherapp.core_network.adapter

import com.weatherapp.core_base.exception.UndefinedException
import com.weatherapp.core_base.result.WorkResult
import com.weatherapp.core_network.exception.NetworkException
import com.weatherapp.core_network.exception.NetworkHttpException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection.HTTP_NO_CONTENT

internal class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, WorkResult<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<WorkResult<T>>) {
        proxy.enqueue(ResultCallback(this, callback))
    }

    override fun cloneImpl(): ResultCall<T> {
        return ResultCall(proxy.clone())
    }

    private class ResultCallback<T>(
        private val proxy: ResultCall<T>,
        private val callback: Callback<WorkResult<T>>
    ) : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            val body = response.body()
            val result = when {
                response.isSuccessful && response.code() == HTTP_NO_CONTENT -> WorkResult.Empty
                response.isSuccessful -> {
                    if (body == null) {
                        WorkResult.Empty
                    } else {
                        WorkResult.Success(body)
                    }
                }
                else -> {
                    val httpException = NetworkHttpException(response.code(), response.message())
                    WorkResult.Error(httpException)
                }
            }

            callback.onResponse(proxy, Response.success(result))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            val result = when (error) {
                is IOException -> WorkResult.Fail(NetworkException(error))
                else -> WorkResult.Fail(UndefinedException(error))
            }

            callback.onResponse(proxy, Response.success(result))
        }
    }

    override fun timeout(): Timeout {
        return proxy.timeout()
    }
}