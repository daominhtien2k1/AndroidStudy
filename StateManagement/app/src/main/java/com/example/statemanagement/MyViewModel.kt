package com.example.statemanagement

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

sealed class ApiResult<out T> {
    object Init: ApiResult<Nothing>()
    object Loading: ApiResult<Nothing>()
    data class Success<T>(val data: T?): ApiResult<T>()
    data class Error(val message: String): ApiResult<Nothing>()
}

class MyViewModel: ViewModel() {

    fun getRealData(): Flow<ApiResult<String?>?> {
        val myRepository = MyRepository()
        val realData = myRepository.getRealData()
        return realData
    }


}