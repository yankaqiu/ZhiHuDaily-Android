package com.example.zhihudaily_android.model


 class ItemBean {
     lateinit var date: String
     lateinit var stories: List<Story>
     lateinit var top_stories: List<TopStory>


     inner class Story(
         var ga_prefix: String,
         var hint: String,
         var id: Int,
         var image_hue: String,
         var images: List<String>,
         var title: String,
         var type: Int,
         var url: String
     )

     inner class TopStory(
         var ga_prefix: String,
         var hint: String,
         var id: Int,
         var image: String,
         var image_hue: String,
         var title: String,
         var type: Int,
         var url: String
     )
 }
data class DetailBean(
    val body: String,
    val css: List<String>,
    val ga_prefix: String,
    val id: String,
    val image: String,
    val image_hue: String,
    val image_source: String,
    val images: List<String>,
    val js: List<String>,
    val section_id: Int,
    val section_name: String,
    val section_thumbnail: String,
    val share_url: String,
    val title: String,
    val type: Int,
    val url: String
)