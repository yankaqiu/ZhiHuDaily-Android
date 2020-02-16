package com.example.zhihudaily_android.presenter

import com.example.zhihudaily_android.contract.NewsContract
import com.example.zhihudaily_android.model.DetailBean
import com.example.zhihudaily_android.model.NewsModel
import com.example.zhihudaily_android.view.StoryContent
import retrofit2.Call
import retrofit2.Response

class NewsPresenter(var DetailView:NewsContract.DetailView) :NewsContract.Presenter {
    private var model = NewsModel()
    private var date: Int? = null
    override fun addData() {
        var call: Call<DetailBean> = model.getNews(DetailView.getID())
        call.enqueue(object : retrofit2.Callback<DetailBean> {
            override fun onFailure(call: Call<DetailBean>, t: Throwable) {
                DetailView.onError()
            }

            override fun onResponse(call: Call<DetailBean>, response: Response<DetailBean>) {
                var datas = response.body()
                if (datas == null)
                    DetailView.onNull()
                else
                    DetailView.upDate(datas)
            }

        })

    }
}