package com.example.statemanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.statemanagement.databinding.ActivityMainBinding

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
            myViewModel.event(MyViewModel.MyViewEvent.GetMyData)
        }
        activityMainBinding.floatingActionButton2.setOnClickListener {
            myViewModel.event(MyViewModel.MyViewEvent.GetOtherData)
        }

        myViewModel.myState.observe(this) { state ->
            when (state.myData) {
                is MyViewModel.ApiResult.Init -> {
                    activityMainBinding.loading.visibility = View.GONE
                    activityMainBinding.textView.visibility = View.VISIBLE
                    activityMainBinding.textView.text = "Init"
                }
                is MyViewModel.ApiResult.Loading -> {
                    activityMainBinding.loading.visibility = View.VISIBLE
                    activityMainBinding.textView.visibility = View.INVISIBLE
                }
                is MyViewModel.ApiResult.Success -> {
                    activityMainBinding.loading.visibility = View.GONE
                    activityMainBinding.textView.visibility = View.VISIBLE
                    activityMainBinding.textView.text = "Hello World!"
                }
                is MyViewModel.ApiResult.Error -> {

                }

                else -> {}
            }

            // như thế này dù cập nhật lại myData thì cũng chạy lại otherData
            // -> bị set lại textView2 và loading chưa kịp hiển thị visible thì đã set lại thành gone rồi
            when (state.otherData) {
                is MyViewModel.ApiResult.Init -> {
                    activityMainBinding.loading.visibility = View.GONE
                    activityMainBinding.textView2.visibility = View.VISIBLE
                    activityMainBinding.textView2.text = "Init 2"
                }
                is MyViewModel.ApiResult.Loading -> {
                    activityMainBinding.loading.visibility = View.VISIBLE
                    activityMainBinding.textView2.visibility = View.INVISIBLE
                }
                is MyViewModel.ApiResult.Success -> {
                    activityMainBinding.loading.visibility = View.GONE
                    activityMainBinding.textView2.visibility = View.VISIBLE
                    activityMainBinding.textView2.text = "Hello World 2!"
                }
                is MyViewModel.ApiResult.Error -> {

                }

                else -> {}
            }

        }

    }
}