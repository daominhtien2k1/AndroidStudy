package com.example.statemanagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class MyViewModel: ViewModel() {
    private val _myState = MutableLiveData<MyViewModelState>(MyViewModelState())
    val myState: LiveData<MyViewModelState>
        get() = _myState

    private val currentState: MyViewModelState?
        get() = _myState.value

    sealed class ApiResult<out T> {
        object Init: ApiResult<Nothing>()
        object Loading: ApiResult<Nothing>()
        data class Success<T>(val data: T?): ApiResult<T>()
        data class Error(val message: String): ApiResult<Nothing>()
    }

    // Cách tiếp cận đầu tiên cho multiple data
    data class MyViewModelState(
        val myData: ApiResult<String> = ApiResult.Init,
        val otherData: ApiResult<Int> = ApiResult.Init
    ): ViewState()

    sealed class MyViewEvent() {
        object GetMyData: MyViewEvent()
        object GetOtherData: MyViewEvent()
    }

    sealed class MyViewEffect: ViewEffect()

    fun event(event: MyViewEvent) {
        when (event) {
            is MyViewEvent.GetMyData -> {
                getMyData()
            }
            is MyViewEvent.GetOtherData -> {
                getOtherData()
            }
        }
    }

    private fun getMyData() {
        _myState.postValue(currentState!!.copy(myData = ApiResult.Loading))
        Timer().schedule(object : TimerTask() {
            override fun run() {
                _myState.postValue(
                    currentState!!.copy(myData = (ApiResult.Success("Call Api success")))
                )
            }
        }, 1000)
    }

    private fun getOtherData() {
        _myState.postValue(currentState!!.copy(otherData = ApiResult.Loading))
        Timer().schedule(object : TimerTask() {
            override fun run() {
                _myState.postValue(
                    currentState!!.copy(otherData = (ApiResult.Success(200)))
                )
            }
        }, 1000)
    }

}

open class ViewState()
open class ViewEvent()
open class ViewEffect()