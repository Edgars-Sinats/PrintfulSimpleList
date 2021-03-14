package com.example.printfulsimplelist.ui.shared

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.printfulsimplelist.api.Article
import com.example.printfulsimplelist.api.NewsRepository

class SharedViewModel(app: Application) : AndroidViewModel(app){
    private val dataRepo = NewsRepository(app)
    val articleData = dataRepo.newsArticle
//    val newsData = dataRepo.newsData1

//    @Bindable
    val selectedArticle = MutableLiveData<Article>()

//    val newsArticle = newsData.val
//    val selectedNews = MutableLiveData<NewsApiJSON>()

    fun refreshData() {
        dataRepo.callDataFromWeb()
    }

}