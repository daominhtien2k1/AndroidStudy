package com.example.recyclerviewmultipleviewtype_realapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recyclerviewmultipleviewtype_realapp.R
import com.example.recyclerviewmultipleviewtype_realapp.model.InfoFoodModel
import com.example.recyclerviewmultipleviewtype_realapp.model.WrapperModel
import com.example.recyclerviewmultipleviewtype_realapp.utils.ItemClickListener

class MenuFoodAdapter(
    private val mContext: Context,
    private val wrapperModel: WrapperModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listFoodInSelectedTab: MutableList<InfoFoodModel>

    init {
        listFoodInSelectedTab = wrapperModel.listRecommend
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_VIEWPAGER -> {
                val viewHeader: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_image_banner, parent, false)
                BannerViewHolder(viewHeader)
            }
            VIEW_GRIDVIEW -> {
                val viewItem: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_article_layout, parent, false)
                ArticleViewHolder(viewItem)
            }
            VIEW_RCVHORIZONTAL -> {
                val viewItem: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_foryou_layout, parent, false)
                ForYouViewHolder(viewItem)
            }
            VIEW_TAB -> {
                val viewItem: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_tab_layout, parent, false)
                TabViewHolder(viewItem)
            }
            VIEW_ITEM -> {
                val viewItem: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_info_food, parent, false)
                InfoFoodHolder(viewItem)
            }
            else -> throw RuntimeException("Could not inflate layout")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> {
                val imageViewPagerAdapter = BannerAdapter(mContext, wrapperModel.listBanner)
                holder.viewPager.adapter = imageViewPagerAdapter
            }

            is ArticleViewHolder -> {
                val gridLayoutManagerVertical = GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false)
                holder.rcvArticle.layoutManager = gridLayoutManagerVertical
                holder.rcvArticle.setHasFixedSize(true)
                val adapter = ArticleAdapter(mContext, wrapperModel.listArticle)
                holder.rcvArticle.adapter = adapter
            }

            is ForYouViewHolder -> {
                holder.rcvForYou.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                holder.rcvForYou.itemAnimator = DefaultItemAnimator()
                holder.rcvForYou.setHasFixedSize(true)
                val forYouAdapter = ForYouAdapter(wrapperModel.listForYou, mContext)
                holder.rcvForYou.adapter = forYouAdapter
            }

            is TabViewHolder -> {
                holder.setItemClickListener(object : ItemClickListener {
                    override fun onItemClick(v: View?, pos: Int) {
                        when (v!!.id) {
                            R.id.btnRecommend -> {
                                listFoodInSelectedTab = wrapperModel.listRecommend
                                notifyDataSetChanged()
                            }

                            R.id.btnNear -> {
                                listFoodInSelectedTab = wrapperModel.listNear
                                notifyDataSetChanged()
                            }

                            R.id.btnPrice -> {
                                listFoodInSelectedTab = wrapperModel.listGoodPrice
                                notifyDataSetChanged()
                            }

                            else -> {}
                        }
                    }
                })

            }

            is InfoFoodHolder -> {
                Glide.with(mContext)
                    .load(listFoodInSelectedTab[position - 4].logo_link)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView)
                holder.tvNameShop.text = listFoodInSelectedTab[position - 4].name
                holder.tvTimmeMin.text = listFoodInSelectedTab[position - 4].shipping_duration_min.toString() + " -"
                holder.tvTimeMax.text = listFoodInSelectedTab[position - 4].shipping_duration_max.toString() + " phút"
                holder.tvDiscount.text = "Giảm " + listFoodInSelectedTab[position - 4].promotion_text
            }
        }
    }

    override fun getItemCount(): Int {
        // +4 item trong recyclerview cha: banner, article, foryou, tab (1 item to có thể là recyclerview)
        // còn các item còn lại là listFoodInSelectedTab
        return listFoodInSelectedTab.size + 4
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                VIEW_VIEWPAGER
            }
            1 -> {
                VIEW_GRIDVIEW
            }
            2 -> {
                VIEW_RCVHORIZONTAL
            }
            3 -> {
                VIEW_TAB
            }
            else -> VIEW_ITEM
        }
    }

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val viewPager: ViewPager = itemView.findViewById<View>(R.id.viewpager) as ViewPager
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rcvArticle: RecyclerView = itemView.findViewById<View>(R.id.rcvArticle) as RecyclerView
    }

    inner class ForYouViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rcvForYou: RecyclerView = itemView.findViewById<View>(R.id.rcvForYou) as RecyclerView
    }

    inner class TabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val btnRecommend: Button
        val btnNear: Button
        val btnPrice: Button
        private var itemClickListener: ItemClickListener? = null

        init {
            btnRecommend = itemView.findViewById<View>(R.id.btnRecommend) as Button
            btnNear = itemView.findViewById<View>(R.id.btnNear) as Button
            btnPrice = itemView.findViewById<View>(R.id.btnPrice) as Button
            btnRecommend.setOnClickListener(this)
            btnNear.setOnClickListener(this)
            btnPrice.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            itemClickListener!!.onItemClick(v, layoutPosition)
        }

        fun setItemClickListener(ic: ItemClickListener?) {
            itemClickListener = ic
        }
    }

    inner class InfoFoodHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById<View>(R.id.imgFood) as ImageView
        val tvNameShop: TextView = itemView.findViewById<View>(R.id.tvNameShop) as TextView
        val tvTimmeMin: TextView = itemView.findViewById<View>(R.id.tvTimeMin) as TextView
        val tvTimeMax: TextView = itemView.findViewById<View>(R.id.tvTimeMax) as TextView
        val tvDiscount: TextView = itemView.findViewById<View>(R.id.tvDisCount) as TextView
    }

    companion object {
        private const val VIEW_VIEWPAGER = 0
        private const val VIEW_GRIDVIEW = 1
        private const val VIEW_RCVHORIZONTAL = 2
        private const val VIEW_TAB = 3
        private const val VIEW_ITEM = 4
    }
}