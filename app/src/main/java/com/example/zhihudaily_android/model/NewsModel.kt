package com.example.zhihudaily_android.model

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NewsModel {
    internal var okHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(9999, TimeUnit.MILLISECONDS)
        .build()

    var retrofit =Retrofit.Builder()
        .baseUrl("http://news-at.zhihu.com/api/3/news/")
        .addConverterFactory(GsonConverterFactory.create())
       .client(okHttpClient)
        .build()
    var service = retrofit.create(NewsApi::class.java)
    fun getNews(id : Int) : Call<DetailBean> {
        return service.getNews(id)
    }

}