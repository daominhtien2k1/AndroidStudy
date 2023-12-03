package com.example.recyclerviewmultipleviewtype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(
    private val typeOneList: MutableList<String>,
    private val typeTwoList: MutableList<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        return when (viewType) {
            ViewType.TYPE_ONE.type -> {
                val view = inflater.inflate(R.layout.type_one, parent, false)
                TypeOneViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.type_two, parent, false)
                TypeTwoViewHolder(view)
            }
        }

    }

    override fun getItemCount(): Int {
        return typeOneList.count() + typeTwoList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.apply {
            when (holder) {
                is TypeOneViewHolder -> holder.bind(typeOneList[position])
                is TypeTwoViewHolder -> holder.bind(typeTwoList[position - typeOneList.count()])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            in 0 until typeOneList.count() -> ViewType.TYPE_ONE.type
            else -> ViewType.TYPE_TWO.type
        }
    }

    inner class TypeOneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContact = itemView.findViewById<TextView>(R.id.tvContact) // layout type_one
        fun bind(item: String) {
            with(itemView) {
                tvContact.text = item
            }

        }

    }

    inner class TypeTwoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContactSupport = itemView.findViewById<TextView>(R.id.tvContact) // layout type_two
        fun bind(item: String) {
            with(itemView) {
                tvContactSupport.text = item

            }
        }

    }

}

enum class ViewType(val type: Int) {
    TYPE_ONE(0),
    TYPE_TWO(1)
}