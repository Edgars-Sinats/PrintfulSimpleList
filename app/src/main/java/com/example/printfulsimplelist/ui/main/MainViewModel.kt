package com.example.printfulsimplelist.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.printfulsimplelist.adapters.NewsRepository

class MainViewModel(app: Application) : AndroidViewModel(app){
    private val dataRepo = NewsRepository(app)
    val newsData = dataRepo.newsData


}