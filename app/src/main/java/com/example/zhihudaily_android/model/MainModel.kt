package com.example.zhihudaily_android.model

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainModel {
    private val BASE_URL="https://news-at.zhihu.com/api/3/news/"

    internal var okHttpClient = OkHttpClient.Builder()
       .retryOnConnectionFailure(true)
        .connectTimeout(9999, TimeUnit.MILLISECONDS)
        .build()

    internal var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private var service = retrofit.create(NewsApi::class.java)

    fun refresh(): Call<ItemBean> {
        return service.list
    }
    fun update(date : String):Call<ItemBean>{
        return service.getList(date)
    }
}