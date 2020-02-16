package com.example.zhihudaily_android.contract

import com.example.zhihudaily_android.model.DetailBean

interface NewsContract {
    interface DetailView{
        fun onNull()
        fun onError()
        fun upDate(data:DetailBean)
        fun getID() : Int
    }
    interface Presenter{
        fun addData()
    }
}