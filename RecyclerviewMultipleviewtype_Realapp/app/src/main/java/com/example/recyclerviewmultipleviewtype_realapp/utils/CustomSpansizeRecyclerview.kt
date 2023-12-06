package com.example.recyclerviewmultipleviewtype_realapp.utils

import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
class CustomSpansizeRecyclerview : SpanSizeLookup() {
    override fun getSpanSize(i: Int): Int {
        if (i in 0..3) {
            return 2
        } else {
           return 1
        }
    }
}