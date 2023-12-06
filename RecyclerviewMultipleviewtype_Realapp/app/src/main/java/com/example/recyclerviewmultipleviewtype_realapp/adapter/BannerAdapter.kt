package com.example.recyclerviewmultipleviewtype_realapp.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recyclerviewmultipleviewtype_realapp.model.BannerModel

class BannerAdapter(
    private val mContext: Context,
    private val listBanner: MutableList<BannerModel>
) : PagerAdapter() {
    override fun getCount(): Int {
        return listBanner.size
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(mContext)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        Glide.with(mContext)
            .load(listBanner[position].image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
        container.addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }
}