package com.example.zhihudaily_android.model

import retrofit2.Call
import com.example.zhihudaily_android.view.StoryContent
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi{
    @get:GET("latest")
    val list: Call<ItemBean>

    @GET("before/{date}")
    fun getList(@Path("date") date:String): Call<ItemBean>

    @GET("{id}")
    fun getNews(@Path("id") id: Int): Call<DetailBean>
}