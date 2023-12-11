package com.example.statemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.statemanagement.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
    }

    override fun onResume() {
        super.onResume()

        activityMainBinding.floatingActionButton.setOnClickListener {
            lifecycleScope.launch {
                myViewModel.getMyData().collect { state ->
                    when (state) {
                        is ApiResult.Init -> {
                            activityMainBinding.loading.visibility = View.GONE
                            activityMainBinding.textView.visibility = View.VISIBLE
                            activityMainBinding.textView.text = "Init"
                        }

                        is ApiResult.Loading -> {
                            activityMainBinding.loading.visibility = View.VISIBLE
                            activityMainBinding.textView.visibility = View.INVISIBLE
                        }

                        is ApiResult.Success -> {
                            activityMainBinding.loading.visibility = View.GONE
                            activityMainBinding.textView.visibility = View.VISIBLE
                            activityMainBinding.textView.text = "Hello World!"
                        }

                        is ApiResult.Error -> {

                        }

                        else -> {}
                    }
                }
            }
        }
    }
}