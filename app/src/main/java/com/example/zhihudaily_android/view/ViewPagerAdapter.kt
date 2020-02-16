package com.example.zhihudaily_android.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.zhihudaily_android.R
import com.example.zhihudaily_android.model.ItemBean

import com.bumptech.glide.Glide

class ViewPagerAdapter (private val context: Context,private val list:List<ItemBean.TopStory >): PagerAdapter() {
    internal  var topStories: MutableList<ItemBean.TopStory> = ArrayList()
    init{
        topStories.addAll(list)
    }
    override fun isViewFromObject(view: View, O: Any): Boolean {
        return view === O
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.banner_layout,container,false)
        val id = arrayOf(topStories[position].id.toString())
        val title = view.findViewById<TextView>(R.id.topTitle)
        val imageView = view.findViewById<ImageView>(R.id.topImage)
        title.text = topStories[position].title

        Glide.with(context)
            .load(topStories[position].image)
            .into(imageView)
        view.setOnClickListener{
            val intent = Intent(context, StoryContent::class.java)
            val bundle = Bundle()
            bundle.putString("id",id[0])
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        }
}
