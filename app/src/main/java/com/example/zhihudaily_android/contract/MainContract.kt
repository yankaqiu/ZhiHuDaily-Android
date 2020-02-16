package com.example.zhihudaily_android.contract

import com.example.zhihudaily_android.model.ItemBean


interface MainContract {
    interface MainView{
        fun onError()
        fun onNull()
        fun initView()
        fun updateView()
        fun setData(datas:ItemBean)
        fun refreshView()
    }
    interface Presenter{
        fun addData()
        fun refreshData()
        fun loadMore(date:String)
        //fun clearData()
    }
}