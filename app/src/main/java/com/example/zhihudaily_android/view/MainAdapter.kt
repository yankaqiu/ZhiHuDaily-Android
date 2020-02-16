package com.example.zhihudaily_android.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView


import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.zhihudaily_android.R
import com.example.zhihudaily_android.model.ItemBean

import kotlin.collections.MutableList as MutableList1

class MainAdapter (private val mContext: Context,private var datas:ItemBean) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
     var stories:ArrayList<ItemBean.Story> = ArrayList()
    var topStories:ArrayList<ItemBean.TopStory> = ArrayList()
    var size = 1

    fun initData(datas:ItemBean){
        this.datas=datas
        this.stories.clear()
        this.topStories.clear()
        this.stories=datas.stories as ArrayList<ItemBean.Story>
        this.topStories=datas.top_stories as ArrayList<ItemBean.TopStory>
        size=stories.size+1
    }
    fun addData(datas: ItemBean){
        this.stories.addAll(datas.stories)
        size=stories.size+1
         notifyItemChanged(size,size+stories.size)
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, t: Int): RecyclerView.ViewHolder {
        var view:View
        var viewHolder:RecyclerView.ViewHolder ? = null
        when(getItemViewType(t)){
            0 -> {
                view = LayoutInflater.from(mContext).inflate(R.layout.banner_layout,parent,false)
                viewHolder = Banner(view)
            }
            1-> {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false)
                viewHolder = ArticleViewHolder(view)
            }
        }
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return stories.size  + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p: Int) {
        var position = p-1
        if(holder is Banner){
            val banner = ViewPagerAdapter(mContext ,topStories)
            holder.viewPager.adapter = banner
        }
        if(holder is ArticleViewHolder){
            val story = stories[position]
            holder.title.text = story.title
            holder.hint.text = story.hint
            Glide.with(mContext)
                .load(story.images[0])
                .into(holder.image)
            holder.layout.setOnClickListener{
                val intent = Intent(mContext,StoryContent::class.java)
                val bundle = Bundle()
                //bundle.putString("")
                intent.putExtras(bundle)
                mContext.startActivity(intent)
            }
        }
    }

    class ArticleViewHolder(  storyView: View) : RecyclerView.ViewHolder(storyView) {
        var title : TextView = storyView.findViewById(R.id.article_name)
        var image : ImageView = storyView.findViewById(R.id.article_image)
        var hint : TextView = storyView.findViewById(R.id.hint)
        var layout : View = storyView.findViewById(R.id.layout)
        }
    class Banner(banner: View) : RecyclerView.ViewHolder(banner){
        var viewPager : ViewPager = banner.findViewById(R.id.topStory)
    }
}