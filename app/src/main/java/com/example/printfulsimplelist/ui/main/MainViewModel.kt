package com.example.printfulsimplelist.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.printfulsimplelist.adapters.NewsRepository
import com.example.printfulsimplelist.api.Article
import com.example.printfulsimplelist.api.NewsApiJSON

class MainViewModel(app: Application) : AndroidViewModel(app){
    private val dataRepo = NewsRepository(app)
    val articleData = dataRepo.newsArticle
    val newsData = dataRepo.newsData1

    val selectedArticle = MutableLiveData<Article>()



//    val newsArticle = newsData.val

    val selectedNews = MutableLiveData<NewsApiJSON>()

    fun refreshData() {
        dataRepo.callDataFromWeb()
    }

}