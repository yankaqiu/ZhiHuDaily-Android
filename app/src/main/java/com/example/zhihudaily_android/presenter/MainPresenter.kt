package com.example.zhihudaily_android.presenter

import android.provider.Contacts
import com.example.zhihudaily_android.contract.MainContract
import com.example.zhihudaily_android.model.ItemBean
import com.example.zhihudaily_android.model.MainModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.IOException

class MainPresenter(var MainView:MainContract.MainView):MainContract.Presenter {
    private var mainModel=MainModel()
    private var date:String?=null
    var item :ItemBean?=null
    override fun addData() {
        val call = mainModel.refresh()
        call.enqueue(object : Callback<ItemBean> {
            override fun onResponse(call: Call<ItemBean>, response: Response<ItemBean>) {
                item = response.body()
                MainView.setData(item!!)
                if (item != null) {
                    date = item!!.date
                    MainView.initView()
                }
            }

            override fun onFailure(call: Call<ItemBean>, t: Throwable) {
                MainView.onError()
                t.printStackTrace()
            }
        })
    }

    override fun refreshData() {
        val call=mainModel.refresh()
        call.enqueue(object : Callback<ItemBean> {
            override fun onResponse(call: Call<ItemBean>, response: Response<ItemBean>) {
                item = response.body()
                MainView.setData(item!!)
                if (item != null) {
                    date = item!!.date
                    MainView.refreshView()
                }
            }

            override fun onFailure(call: Call<ItemBean>, t: Throwable) {
                MainView.onError()
                t.printStackTrace()
            }
        })
    }



    override fun loadMore(date: String) {
        val call=mainModel.update(date!!)
        call.enqueue(object : Callback<ItemBean> {
            override fun onResponse(call: Call<ItemBean>, response: Response<ItemBean>) {
                item = response.body()
                MainView.setData(item!!)
                if (item != null) {
                    MainView.updateView()
                }
            }

            override fun onFailure(call: Call<ItemBean>, t: Throwable) {
                MainView.onError()
                t.printStackTrace()
            }
        })
    }


}