package com.example.statemanagement

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

sealed class ApiResult<out T> {
    object Init: ApiResult<Nothing>()
    object Loading: ApiResult<Nothing>()
    data class Success<T>(val data: T?): ApiResult<T>()
    data class Error(val message: String): ApiResult<Nothing>()
}

class MyViewModel: ViewModel() {
    fun getMyData(): Flow<ApiResult<String>> {
        val myRepository = MyRepository()

        return flow {
            emit(ApiResult.Init)
            delay(1000)
            emit(ApiResult.Loading)
            delay(1000)
            val result = myRepository.getMyData()
            emit(ApiResult.Success(result))
        }
    }

}
