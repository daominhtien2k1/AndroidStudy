package com.example.stateuicomponent

interface SwitchObserver {
    fun onSwitchStateChanged(newState: Boolean)
}
class ObservableSwitchState {
    private var switchState: Boolean = true
    private val switchObservers = mutableListOf<SwitchObserver>()

    fun addObserver(switchObserver: SwitchObserver) {
        switchObservers.add(switchObserver)
    }

    fun removeObserver(switchObserver: SwitchObserver) {
        switchObservers.remove(switchObserver)
    }

    fun getSwitchState(): Boolean {
        return switchState
    }

    fun toggleSwitch() {
        switchState = !switchState
        notifyObservers()
    }

    private fun notifyObservers() {
        for (switchObserver in switchObservers) {
            switchObserver.onSwitchStateChanged(switchState)
        }
    }
}
