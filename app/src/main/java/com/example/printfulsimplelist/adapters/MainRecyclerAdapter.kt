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

class MainRecyclerAdapter(val context: Context,
                          val articleApi: List<Article>,
                            val itemListener: ArticleItemListener):
        RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView){
        val itemTitle: TextView = itemView.findViewById(R.id.tv_title)
        val itemDetail: TextView = itemView.findViewById(R.id.tv_description)
        val itemPicture: ImageView = itemView.findViewById(R.id.iv_image)
            }

    interface ArticleItemListener {
        fun onArticleItemClick(news: Article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val response = articleApi[position]
//        for (news in news.articles) {
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

        holder.itemView.setOnClickListener {
            itemListener.onArticleItemClick(response)
        }
    }

    override fun getItemCount(): Int {
        return articleApi.size
    }

}