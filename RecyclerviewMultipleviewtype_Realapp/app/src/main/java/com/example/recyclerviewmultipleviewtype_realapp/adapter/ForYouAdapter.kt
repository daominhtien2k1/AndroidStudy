package com.example.recyclerviewmultipleviewtype_realapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recyclerviewmultipleviewtype_realapp.R
import com.example.recyclerviewmultipleviewtype_realapp.model.InfoFoodModel

class ForYouAdapter(
    private val listInfoFood: MutableList<InfoFoodModel>,
    private val mContext: Context
) :
    RecyclerView.Adapter<ForYouAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_info_food, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        Glide.with(mContext)
            .load(listInfoFood[i].logo_link)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(viewHolder.imageView)
        viewHolder.tvNameShop.text = listInfoFood[i].name
        viewHolder.tvTimmeMin.text = listInfoFood[i].shipping_duration_min.toString() + " -"
        viewHolder.tvTimeMax.text = listInfoFood[i].shipping_duration_max.toString() + " phút"
        viewHolder.tvDiscount.text = "Giảm " + listInfoFood[i].promotion_text
    }

    override fun getItemCount(): Int {
        return listInfoFood.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView
        val tvNameShop: TextView
        val tvTimmeMin: TextView
        val tvTimeMax: TextView
        val tvDiscount: TextView

        init {
            imageView = itemView.findViewById<View>(R.id.imgFood) as ImageView
            tvNameShop = itemView.findViewById<View>(R.id.tvNameShop) as TextView
            tvTimmeMin = itemView.findViewById<View>(R.id.tvTimeMin) as TextView
            tvTimeMax = itemView.findViewById<View>(R.id.tvTimeMax) as TextView
            tvDiscount = itemView.findViewById<View>(R.id.tvDisCount) as TextView
        }
    }
}