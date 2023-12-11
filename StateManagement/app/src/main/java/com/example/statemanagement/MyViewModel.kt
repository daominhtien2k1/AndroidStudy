package com.example.statemanagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class MyViewModel: ViewModel() {
    private val _myDataState = MutableLiveData<MyDataState>(MyDataState())
    val myDataState: LiveData<MyDataState>
        get() = _myDataState

    private val _otherDataState = MutableLiveData<OtherDataState>(OtherDataState())
    val otherDataState: LiveData<OtherDataState>
        get() = _otherDataState

    sealed class ApiResult<out T> {
        object Init: ApiResult<Nothing>()
        object Loading: ApiResult<Nothing>()
        data class Success<T>(val data: T?): ApiResult<T>()
        data class Error(val message: String): ApiResult<Nothing>()
    }

    // Cách tiếp cận thứ hai cho multiple data
    data class MyDataState(
        val myData: ApiResult<String> = ApiResult.Init,
    ): ViewState()

    data class OtherDataState(
        val otherData: ApiResult<Int> = ApiResult.Init
    ): ViewState()

    sealed class MyViewModelEvent() {
        object GetMyData: MyViewModelEvent()
        object GetOtherData: MyViewModelEvent()
    }

    sealed class MyViewModelEffect: ViewEffect()

    fun event(event: MyViewModelEvent) {
        when (event) {
            is MyViewModelEvent.GetMyData -> {
                getMyData()
            }
            is MyViewModelEvent.GetOtherData -> {
                getOtherData()
            }
        }
    }

    private fun getMyData() {
        _myDataState.postValue(
            MyDataState(
                myData = ApiResult.Loading
            )
        )
        Timer().schedule(object : TimerTask() {
            override fun run() {
                _myDataState.postValue(MyDataState(myData = ApiResult.Success("Call Api success")))
            }
        }, 1000)
    }

    private fun getOtherData() {
        _otherDataState.postValue(
            OtherDataState(
                otherData = ApiResult.Loading
            )
        )
        Timer().schedule(object : TimerTask() {
            override fun run() {
                _otherDataState.postValue(OtherDataState(otherData = ApiResult.Success(200)))
            }
        }, 1000)
    }

}

open class ViewState()
open class ViewEvent()
open class ViewEffect()