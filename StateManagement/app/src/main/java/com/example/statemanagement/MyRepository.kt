package com.example.statemanagement

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.delay
import kotlin.random.Random

class Response<T>(val data: T?)

fun <T> toResultFlow(call: suspend () -> Response<T>?): Flow<ApiResult<T>?> {
    return flow<ApiResult<T>?> {
        emit(ApiResult.Loading)

        try {
            emit(ApiResult.Loading)
            val response = call()
            delay(1000)
            if (response != null) {
                emit(ApiResult.Success(response.data))
            } else {
                emit(ApiResult.Error("Random causes an error"))
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(e.toString()))
        }

    }.flowOn(Dispatchers.IO)
}

class MyRepository {
    fun getRealData() = toResultFlow {
        callAPI()
    }

    private fun callAPI(): Response<String?>? {
        val randomSuccess = Random.nextBoolean()

        return if (randomSuccess) {
            Response("API response success")
        } else {
            null
        }
    }
}