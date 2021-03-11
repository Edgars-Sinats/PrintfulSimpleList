package com.example.printfulsimplelist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.printfulsimplelist.R
import com.example.printfulsimplelist.api.Article
import com.example.printfulsimplelist.api.NewsApiJSON

class MainRecyclerAdapter(val context: Context,
                          val newsApi: List<NewsApiJSON>):
        RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView){
        val itemTitle: TextView = itemView.findViewById(R.id.tv_title)
        val itemDetail: TextView = itemView.findViewById(R.id.tv_description)
        val itemPicture: ImageView = itemView.findViewById(R.id.iv_image)
            }

    interface NewsItemListener {
        fun onNewsItemClick(news: Article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsApi[0]
        val response = news.articles[position]
//        for (news in news.articles) {
//
//        }

        with(holder) {
            itemTitle?.let {
                it.text = response.title
                it.contentDescription = response.title
            }
            itemDetail?.let {
                it.text = response.description
                it.contentDescription = response.description
            }
            Glide.with(context)
                    .load(response.urlToImage)
                    .into(itemPicture)
        }

//        holder.itemView.setOnClickListener {
//            itemListenerItem.onNewsItemClick(news)
//        }
    }

    override fun getItemCount(): Int {
        return newsApi[0].articles.size
    }

}