package com.example.recyclerviewscrollvaryingheight

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView


class CoolAdapter : RecyclerView.Adapter<CoolAdapter.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textView: TextView

        init {
            textView = v.findViewById<TextView>(R.id.text_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textLayout = LayoutInflater.from(parent.context).inflate(R.layout.content_layout, parent, false) as ConstraintLayout
        return ViewHolder(textLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val height = (Math.random() * LIST_SIZE * 10).toInt() // 100px khi render ra là 28dp
        holder.textView.height = height
        holder.textView.text = "Position $position: $height px"
        val r = (Math.random() * 255).toInt()
        val g = (Math.random() * 255).toInt()
        val b = (Math.random() * 255).toInt()
        holder.itemView.setBackgroundColor(Color.argb(255, r, g, b))
    }

    override fun getItemCount(): Int {
        return LIST_SIZE
    }

    companion object {
        private const val LIST_SIZE = 50
    }
}
