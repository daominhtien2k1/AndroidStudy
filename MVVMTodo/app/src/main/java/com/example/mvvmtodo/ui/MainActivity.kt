package com.example.mvvmtodo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmtodo.R
import com.example.mvvmtodo.databinding.ActivityMainBinding
import com.example.mvvmtodo.ui.tasks.TasksFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        setSupportActionBar(activityMainBinding.toolbar)
    }

    override fun onStart() {
        super.onStart()
        val tasksFragment = TasksFragment()
        supportFragmentManager.beginTransaction().add(R.id.host_fragment, tasksFragment).commit()
    }

}