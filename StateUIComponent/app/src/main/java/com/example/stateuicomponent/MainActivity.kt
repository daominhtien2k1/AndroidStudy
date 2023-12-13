package com.example.stateuicomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    val observableSwitchState = ObservableSwitchState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        observableSwitchState.addObserver(object : SwitchObserver {
            override fun onSwitchStateChanged(newState: Boolean) {
                when (newState) {
                    true -> {
                        textView.text = "On"
                    }
                    false -> {
                        textView.text = "Off"
                    }

                }
            }
        })

        findViewById<Button>(R.id.switchBtn2).setOnClickListener {
            observableSwitchState.toggleSwitch()
        }
    }
}