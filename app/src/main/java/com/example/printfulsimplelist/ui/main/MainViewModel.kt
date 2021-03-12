package com.example.printfulsimplelist.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.printfulsimplelist.adapters.NewsRepository
import com.example.printfulsimplelist.api.NewsApiJSON

class MainViewModel(app: Application) : AndroidViewModel(app){
    private val dataRepo = NewsRepository(app)
    val newsData = dataRepo.newsData1
//    val newsArticle = newsData.val

    val selectedNews = MutableLiveData<NewsApiJSON>()

    fun refreshData() {
        dataRepo.refreshDataFromWeb()
    }

}