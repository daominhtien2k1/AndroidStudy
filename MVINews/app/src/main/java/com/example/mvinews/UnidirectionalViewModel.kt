package com.example.mvinews

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface UnidirectionalViewModel<S, EF, EV> {
    val state: StateFlow<S>
    val effect: SharedFlow<EF>
    fun event(event: EV)
}