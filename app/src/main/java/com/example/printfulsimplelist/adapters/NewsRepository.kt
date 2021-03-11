package com.example.printfulsimplelist.adapters

import android.app.Application
import android.graphics.Insets.add
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.core.view.OneShotPreDrawListener.add
import androidx.lifecycle.MutableLiveData
import com.example.printfulsimplelist.*
import com.example.printfulsimplelist.api.Article
import com.example.printfulsimplelist.api.NewsApiJSON
import com.example.printfulsimplelist.data.NewsServices
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory

class NewsRepository (val app: Application){

    var newsData = MutableLiveData<List<NewsApiJSON>>()
    val newsArticle = MutableLiveData<List<Article>>()

    init {
        Log.i(LOG_TAG, "NewsRepository 2")

        CoroutineScope(Dispatchers.IO).launch {
            getNewsService()
        }
    }

    @WorkerThread
    suspend fun getNewsService() {
//        val moshi = Moshi.Builder()
//                .add(KotlinJsonAdapterFactory())
//                .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(GLOBAL_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service = retrofit.create(NewsServices::class.java)
        val serviceData = service.getLvNewsData().body() ?: emptyList()
        newsData.postValue(serviceData)

        Log.i(LOG_TAG, "NewsRepository 1")
//        Log.i(LOG_TAG, "Service: ${service.toString()}")
//        Log.i(LOG_TAG, "Service: ${serviceData}")

//        newsData.postValue(listOf(serviceData))
//        val adapter: JsonAdapter<List<NewsApiJSON>> =
    }
//    private fun readData(): List<NewsApiJSON> {
//
//        val moshi = Moshi.Builder().build()
//        val listType = Types.newParameterizedType(List::class.java, NewsApiJSON::class.java)
//        val adapter: JsonAdapter<List<NewsApiJSON>> = moshi.adapter(listType)
////        return adapter.fromJson() ?: emptyList()
//    }

}