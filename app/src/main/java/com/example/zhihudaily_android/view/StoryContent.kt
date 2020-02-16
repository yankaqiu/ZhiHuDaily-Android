package com.example.zhihudaily_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.Toast
import com.example.zhihudaily_android.R
import com.example.zhihudaily_android.contract.NewsContract
import com.example.zhihudaily_android.model.DetailBean
import com.example.zhihudaily_android.presenter.NewsPresenter

class StoryContent : AppCompatActivity(),NewsContract.DetailView {
    private var id : Int ?=null
    var presenter = NewsPresenter(this)
    private lateinit var webView: WebView


    override fun onNull() {
            Toast.makeText(this,"没有找到想要的内容鸭",Toast.LENGTH_SHORT).show()
    }

    override fun onError() {
            Toast.makeText(this,"网络有问题鸭",Toast.LENGTH_SHORT).show()
    }

    override fun upDate(data: DetailBean) {
        var storyBody : String = data.body
        storyBody.replace("<img", "<img style='max-width:100%;height:auto;'")
        webView.loadDataWithBaseURL(null, "<html><body>$storyBody</body></html", "text/html", "utf-8", null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.story_content)

        var intent = intent
        id = intent.getIntExtra("id",0)
//      var bundle = intent.extras
//       if(bundle != null) {
//        id = bundle.getInt("id")
//       }
        webView = findViewById(R.id.storyContent)
        val settings = webView.settings
        settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        settings.setSupportZoom(true)
        presenter.addData()
        // webView.loadUrl("http://daily.zhihu.com/story/" + id)
    }

    override fun getID(): Int {
        return id as Int
    }

}
