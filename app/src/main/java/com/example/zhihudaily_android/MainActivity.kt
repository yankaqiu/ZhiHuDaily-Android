package com.example.zhihudaily_android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Dataset
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.zhihudaily_android.contract.MainContract
import com.example.zhihudaily_android.model.ItemBean

import com.example.zhihudaily_android.presenter.MainPresenter
import com.example.zhihudaily_android.view.MainAdapter
import com.example.zhihudaily_android.view.ViewPagerAdapter

class MainActivity : AppCompatActivity(),MainContract.MainView {

    private lateinit var recyclerView:RecyclerView
    //private val myList=ArrayList<Story>()
   // private val topList=ArrayList<TopStory>()
    var isLoading= false
    private lateinit var mainAdapter:MainAdapter
    private lateinit var refresh:SwipeRefreshLayout
    private var viewPagerAdapter:ViewPagerAdapter?=null
    private var presenter=MainPresenter(this)
    lateinit var datas:ItemBean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recycler_view)
      //  setLayout()
        presenter.addData()
    }

    fun setLayout(){
        mainAdapter= MainAdapter(this,datas)
        recyclerView = findViewById(R.id.recycler_view)
        refresh = findViewById(R.id.refresh)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mainAdapter
    }

    override fun onError() {
        isLoading=true
        Toast.makeText(this,"网络错误",Toast.LENGTH_SHORT).show()
    }

    override fun onNull() {
        isLoading=true
        Toast.makeText(this,"啥都没有",Toast.LENGTH_SHORT).show()


    }

    override fun initView(){
            setLayout()
            uploadMore()
            setRefresh()
            mainAdapter.initData(datas)
            refresh.isRefreshing=false
            mainAdapter.notifyDataSetChanged()
    }

    override fun updateView() {
        mainAdapter.addData(datas)
        mainAdapter.notifyDataSetChanged()
    }

    override fun setData(datas: ItemBean) {
        this.datas=datas
    }

    override fun refreshView() {
        initView()
        mainAdapter.initData(datas)
        refresh.isRefreshing=false
        mainAdapter.notifyDataSetChanged()
    }
//上拉加载
    private fun uploadMore() {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val layoutManager = recyclerView.layoutManager
            val visibleCount = layoutManager!!.childCount
            val totalCount = layoutManager.itemCount
            val lastVisiableItemPos = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            if (visibleCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                && lastVisiableItemPos >= totalCount - 1
                && !isLoading) {
                presenter.loadMore(datas.date)
               // Toast.makeText(this,"前一天的新闻鸭",Toast.LENGTH_SHORT).show()
            }
        }
    })
}

//下拉刷新
    private fun setRefresh() {
        refresh.setOnRefreshListener{
            presenter.refreshData()
        }
    }


}
