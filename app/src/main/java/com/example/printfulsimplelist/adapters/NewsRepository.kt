package com.example.printfulsimplelist.adapters

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.printfulsimplelist.APIRequest
import com.example.printfulsimplelist.GLOBAL_BASE_URL
import com.example.printfulsimplelist.WEB_SERVICE_URL
import com.example.printfulsimplelist.api.Article
import com.example.printfulsimplelist.api.NewsApiJSON
import com.example.printfulsimplelist.data.NewsServices
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class NewsRepository (val app: Application){

    val newsData = MutableLiveData<List<NewsApiJSON>>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            getNewsService()
        }
    }

    @WorkerThread
    suspend fun getNewsService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(GLOBAL_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service = retrofit.create(NewsServices::class.java)
        val serviceData = service.getLvNewsData().body() ?: emptyList()

        newsData.postValue(serviceData)
//        val adapter: JsonAdapter<List<NewsApiJSON>> =
    }


}