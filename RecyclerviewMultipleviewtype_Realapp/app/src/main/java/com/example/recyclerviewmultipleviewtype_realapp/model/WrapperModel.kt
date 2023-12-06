package com.example.recyclerviewmultipleviewtype_realapp.model

class WrapperModel(
    var listBanner: MutableList<BannerModel>,
    var listArticle: MutableList<BannerModel>,
    var listForYou: MutableList<InfoFoodModel>,
    var listRecommend: MutableList<InfoFoodModel>,
    var listNear: MutableList<InfoFoodModel>,
    var listGoodPrice: MutableList<InfoFoodModel>
)