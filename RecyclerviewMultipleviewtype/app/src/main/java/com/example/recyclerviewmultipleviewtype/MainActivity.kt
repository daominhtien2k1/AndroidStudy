package com.example.recyclerviewmultipleviewtype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    fun initView() {
        var recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MainAdapter(
            mutableListOf<String>("type 1 1", "type 1 2", "type 1 3", "type 1 4", "type 1 5"),
            mutableListOf<String>("type 2 1", "type 2 2", "type 2 3", "type 2 4", "type 2 5"),
        )
    }

}