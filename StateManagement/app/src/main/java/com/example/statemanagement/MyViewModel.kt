package com.example.statemanagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class MyViewModel: ViewModel() {
    private val _myData = MutableLiveData<MyViewModelState>(MyViewModelState(ApiResult.Init))
    val myData: LiveData<MyViewModelState>
        get() = _myData

    // nếu là ApiResult<T> thì báo lỗi
    sealed class ApiResult<out T> {
        object Init: ApiResult<Nothing>()
        object Loading: ApiResult<Nothing>()
        data class Success<T>(val data: T?): ApiResult<T>()
        data class Error(val message: String): ApiResult<Nothing>()
    }

    data class MyViewModelState(
        val myData: ApiResult<String>
    ): ViewState()

    sealed class MyViewModelEvent() {
        object GetMyData: MyViewModelEvent()
    }

    sealed class MyViewModelEffect: ViewEffect()

    fun event(event: MyViewModelEvent) {
        when (event) {
            is MyViewModelEvent.GetMyData -> {
                getMyData()
            }
        }
    }

    private fun getMyData() {
        _myData.postValue(MyViewModelState(ApiResult.Loading)) // nếu là ApiResult<T> thì báo lỗi
        Timer().schedule(object : TimerTask() {
            override fun run() {
                _myData.postValue(MyViewModelState(ApiResult.Success("Call Api success")))
            }
        }, 1000)
    }

}

open class ViewState()
open class ViewEvent()
open class ViewEffect()
