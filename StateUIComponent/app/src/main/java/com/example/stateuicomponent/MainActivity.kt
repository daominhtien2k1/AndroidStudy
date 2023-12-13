package com.example.stateuicomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    var switchStatus: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        // như thế này không dynamic render được
        textView.text = if(switchStatus) "On" else "Off"

        findViewById<SwitchCompat>(R.id.switchBtn).setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                textView.text = if (isChecked) "On" else "Off"
            }

        })

        // như thế này không dynamic render được
        findViewById<Button>(R.id.switchBtn2).setOnClickListener {
            switchStatus = !switchStatus
        }
    }
}