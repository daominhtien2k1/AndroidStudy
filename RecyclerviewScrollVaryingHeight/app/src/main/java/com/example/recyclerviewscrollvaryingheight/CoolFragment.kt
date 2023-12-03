package com.example.recyclerviewscrollvaryingheight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CoolFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val context = container?.context
        val layout = inflater.inflate(R.layout.cool_fragment, container, false) as RecyclerView
        layout.adapter = CoolAdapter()
        if (getArguments() != null) {
            val position: Int = getArguments()?.getInt("position") ?:  0
            when (position) {
                0 -> {
                    // default là isSmoothScrollbarEnabled: true
                    val linearLayoutManager= LinearLayoutManager(context)
                    layout.layoutManager = linearLayoutManager
                    return layout
                }

                1 -> {
                    val linearLayoutManagerNoSmooth = LinearLayoutManager(context)
                    linearLayoutManagerNoSmooth.isSmoothScrollbarEnabled = false
                    layout.layoutManager = linearLayoutManagerNoSmooth
                    return layout
                }

                2 -> {
                    val linearLayoutManagerSmooth = LinearLayoutManager(context)
                    linearLayoutManagerSmooth.isSmoothScrollbarEnabled = true
                    layout.layoutManager = linearLayoutManagerSmooth
                    return layout
                }

                3 -> {
                    val coolLayoutManager = CoolLayoutManager(context)
                    layout.layoutManager = coolLayoutManager
                    return layout
                }

                else -> {}
            }
        }
        return layout
    }

}