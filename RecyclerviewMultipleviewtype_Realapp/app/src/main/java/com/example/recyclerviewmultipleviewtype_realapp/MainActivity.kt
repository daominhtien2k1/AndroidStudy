package com.example.recyclerviewmultipleviewtype_realapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewmultipleviewtype_realapp.adapter.MenuFoodAdapter
import com.example.recyclerviewmultipleviewtype_realapp.model.BannerModel
import com.example.recyclerviewmultipleviewtype_realapp.model.InfoFoodModel
import com.example.recyclerviewmultipleviewtype_realapp.model.WrapperModel
import com.example.recyclerviewmultipleviewtype_realapp.utils.CustomSpansizeRecyclerview

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: MenuFoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init();
    }

    private fun init() {
        findViewById()
        initRCV()
        setAdapterRCV()
    }

    private fun findViewById() {
        recyclerView = findViewById<View>(R.id.rcvMenu) as RecyclerView
    }

    private fun initRCV() {
        val gridLayoutManagerVertical =
            GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        gridLayoutManagerVertical.spanSizeLookup = CustomSpansizeRecyclerview()
        recyclerView.setLayoutManager(gridLayoutManagerVertical)
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemAnimator(DefaultItemAnimator())
    }

    private fun setAdapterRCV() {
        val wrapperModel = WrapperModel(
            getListBanner(),
            getListArticle(),
            getListForYou(),
            getListRecommend(),
            getListNear(),
            getListGoodPrice()
        )
        mAdapter = MenuFoodAdapter(this, wrapperModel)
        recyclerView.setAdapter(mAdapter)
    }

    private fun getListBanner(): MutableList<BannerModel> {
        val arBanner = mutableListOf<BannerModel>()
        arBanner.add(BannerModel(1, R.drawable.banner_1))
        arBanner.add(BannerModel(1, R.drawable.banner_2))
        arBanner.add(BannerModel(1, R.drawable.banner_3))
        return arBanner
    }

    private fun getListArticle(): MutableList<BannerModel> {
        val arArticle = mutableListOf<BannerModel>()
        arArticle.add(BannerModel(1, R.drawable.article_1))
        arArticle.add(BannerModel(1, R.drawable.article_2))
        arArticle.add(BannerModel(1, R.drawable.article_3))
        return arArticle
    }

    private fun getListForYou(): MutableList<InfoFoodModel> {
        val arInfoFood = mutableListOf<InfoFoodModel>()
        arInfoFood.apply {
            add(InfoFoodModel(10, "Đặc Sản Nắng Gió - Thịt, Hải Sản & Khô Các Loại", R.drawable.image_food_10, 0, 10, "100%"));
            add(InfoFoodModel(11, "Dimsum Điểm Tâm Đài Loan - Nguyễn Biểu", R.drawable.image_food_11, 10, 20, "40%"));
            add(InfoFoodModel(12, "Royal Dimsum - Shop Online", R.drawable.image_food_12, 5, 10, "10%"));
            add(InfoFoodModel(13, "Vườn Xưa - Bánh Cuốn Thịt Nướng", R.drawable.image_food_13, 0, 10, "50%"));
            add(InfoFoodModel(14, "Trà Sữa Cha Cha", R.drawable.image_food_14, 0, 10, "10%"));
            add(InfoFoodModel(15, "Bếp Văn Chương - Cơm Gà Sốt Cay Hàn Quốc", R.drawable.image_food_15, 0, 10, "100%"));
        }
        return arInfoFood
    }

    private fun getListRecommend(): MutableList<InfoFoodModel> {
        val arInfoFood = mutableListOf<InfoFoodModel>()
        arInfoFood.apply {
            add(InfoFoodModel(1, "Kai Coffee - Đường C18", R.drawable.image_food_1, 10, 20, "60%"));
            add(InfoFoodModel(2, "BlackBall - Chè & Trà Sữa Đài Loan - Khu Du Lịch Bến Xưa", R.drawable.image_food_2, 15, 20, "30%"));
            add(InfoFoodModel(3, "Oppa Milk Tea", R.drawable.image_food_3, 0, 10, "20%"));
            add(InfoFoodModel(4, "BiBi Fruits - Trái Cây Sân Bay", R.drawable.image_food_4, 20, 40, "80%"));
            add(InfoFoodModel(5, "Cửa Hàng Trái Cây Farm Của Tùng", R.drawable.image_food_5, 10, 20, "50%"));
            add(InfoFoodModel(6, "Chợ Phố Fresh Food - Trái Cây Nhập Khẩu", R.drawable.image_food_6, 10, 20, "40%"));
            add(InfoFoodModel(7, "La Maison - Thịt Ngon Quốc Tế - Hoàng Hoa Thám", R.drawable.image_food_7, 5, 10, "10%"));
            add(InfoFoodModel(8, "Shop Rượu Vang Trái Ngọt", R.drawable.image_food_8, 0, 10, "50%"));
            add(InfoFoodModel(9, "Cá Hồi Annaseafresh - Phan Đình Phùng", R.drawable.image_food_9, 0, 10, "10%"));
            add(InfoFoodModel(10, "Đặc Sản Nắng Gió - Thịt, Hải Sản & Khô Các Loại", R.drawable.image_food_10, 0, 10, "100%"));
            add(InfoFoodModel(11, "Dimsum Điểm Tâm Đài Loan - Nguyễn Biểu", R.drawable.image_food_11, 10, 20, "40%"));
            add(InfoFoodModel(12, "Royal Dimsum - Shop Online", R.drawable.image_food_12, 5, 10, "10%"));
            add(InfoFoodModel(13, "Vườn Xưa - Bánh Cuốn Thịt Nướng", R.drawable.image_food_13, 0, 10, "50%"));
            add(InfoFoodModel(14, "Trà Sữa Cha Cha", R.drawable.image_food_14, 0, 10, "10%"));
            add(InfoFoodModel(15, "Bếp Văn Chương - Cơm Gà Sốt Cay Hàn Quốc", R.drawable.image_food_15, 0, 10, "100%"));
        }
        return arInfoFood
    }

    private fun getListNear(): MutableList<InfoFoodModel> {
        val arInfoFood = mutableListOf<InfoFoodModel>()
        arInfoFood.apply {
            add(InfoFoodModel(4, "BiBi Fruits - Trái Cây Sân Bay", R.drawable.image_food_4, 20, 40, "80%"));
            add(InfoFoodModel(5, "Cửa Hàng Trái Cây Farm Của Tùng", R.drawable.image_food_5, 10, 20, "50%"));
            add(InfoFoodModel(6, "Chợ Phố Fresh Food - Trái Cây Nhập Khẩu", R.drawable.image_food_6, 10, 20, "40%"));
            add(InfoFoodModel(7, "La Maison - Thịt Ngon Quốc Tế - Hoàng Hoa Thám", R.drawable.image_food_7, 5, 10, "10%"));
            add(InfoFoodModel(8, "Shop Rượu Vang Trái Ngọt", R.drawable.image_food_8, 0, 10, "50%"));
            add(InfoFoodModel(9, "Cá Hồi Annaseafresh - Phan Đình Phùng", R.drawable.image_food_9, 0, 10, "10%"));
            add(InfoFoodModel(10, "Đặc Sản Nắng Gió - Thịt, Hải Sản & Khô Các Loại", R.drawable.image_food_10, 0, 10, "100%"));
        }
        return arInfoFood
    }

    private fun getListGoodPrice(): MutableList<InfoFoodModel> {
        val arInfoFood = mutableListOf<InfoFoodModel>()
        arInfoFood.apply {
            add(InfoFoodModel(10, "Đặc Sản Nắng Gió - Thịt, Hải Sản & Khô Các Loại", R.drawable.image_food_10, 0, 10, "100%"));
            add(InfoFoodModel(11, "Dimsum Điểm Tâm Đài Loan - Nguyễn Biểu", R.drawable.image_food_11, 10, 20, "40%"));
            add(InfoFoodModel(12, "Royal Dimsum - Shop Online", R.drawable.image_food_12, 5, 10, "10%"));
            add(InfoFoodModel(13, "Vườn Xưa - Bánh Cuốn Thịt Nướng", R.drawable.image_food_13, 0, 10, "50%"));
            add(InfoFoodModel(14, "Trà Sữa Cha Cha", R.drawable.image_food_14, 0, 10, "10%"));
            add(InfoFoodModel(15, "Bếp Văn Chương - Cơm Gà Sốt Cay Hàn Quốc", R.drawable.image_food_15, 0, 10, "100%"));
        }
        return arInfoFood
    }

}