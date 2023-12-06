package com.example.recyclerviewmultipleviewtype_realapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recyclerviewmultipleviewtype_realapp.R
import com.example.recyclerviewmultipleviewtype_realapp.model.BannerModel

class ArticleAdapter(
    private val mContext: Context,
    private val listGrid: MutableList<BannerModel>
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        val viewHold: ViewHolder
        when (viewType) {
            0 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_gridview, parent, false)
                viewHold = ViewHolder(view, viewType)
                return viewHold
            }

            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_gridview, parent, false)
                viewHold = ViewHolder(view, viewType)
                return viewHold
            }
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Glide.with(mContext)
            .load(listGrid[position].image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewHolder.imgView)
    }

    override fun getItemCount(): Int {
        return 3
    }

    inner class ViewHolder(itemView: View, i: Int) : RecyclerView.ViewHolder(itemView) {
        val imgView: ImageView = itemView.findViewById<View>(R.id.imgGrid) as ImageView

    }
}