package com.example.printfulsimplelist.adapters

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.printfulsimplelist.*
import com.example.printfulsimplelist.api.Article
import com.example.printfulsimplelist.api.NewsApiJSON
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

class NewsRepository (val app: Application){

    var newsData = MutableLiveData<List<NewsApiJSON>>()
    val newsData1 = MutableLiveData<NewsApiJSON>()
    val newsArticle = MutableLiveData<List<Article>>()

    private val listTypeArticle = Types.newParameterizedType(
            List::class.java, Article::class.java
    )

    init {
        Log.i(LOG_TAG, "Bik talak 5")

        callDataFromWeb()
//        val data = readDataFromCache()
//        if (data.isEmpty()) {
//        } else {
//            newsData.value = data
//            Log.i(LOG_TAG, "Using local data")
//        }
        Log.i(LOG_TAG, "NewsRepository 2")

//        CoroutineScope(Dispatchers.IO).launch {
//            getNewsService()
//        }
    }

    fun callDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()

        //            getNewsService()
//            getTextFromAssets()
        }
    }

    fun getTextFromAssets(){
        val data = FileHelper.getTextFromAssets(app, "apiLocal.json")
        fun parseText(text: String) {
            val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            val adapter: JsonAdapter<NewsApiJSON> =
                    moshi.adapter(listTypeArticle)
//            newsData2 = adapter.fromJson(text)
            newsData1.value = adapter.fromJson(data)
            val newsD = adapter.fromJson(data)
            Log.i(LOG_TAG,
                        "${newsD!!.articles} (\$${newsD.status})")
        }
    }


    @WorkerThread
    suspend fun callWebService() {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(GLOBAL_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val service = retrofit.create(APIRequest::class.java)
//        val response = service.getNews()
        val response = service.getNews().body()?.articles ?: emptyList()
        newsArticle.postValue(response)


        // Do the GET request and get response
//        withContext(Dispatchers.Main) {
//            if (response.isSuccessful) {
//                val items = response.body()?.articles
//                if (items != null) {
//                    for (i in 0 until items.count()) {
//                        var author  = items[i].author ?: "N/A"
////                        Log.d("Author: ", author)
//                    }
//                }
//            }
//        }

//        newsData1.postValue(serviceData)
//        saveDataToCache(sData)
        Log.i(LOG_TAG, "Bik talak 3")


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

    private fun saveDataToCache(newsData: NewsApiJSON) {
//        if (ContextCompat.checkSelfPermission(app,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                == PackageManager.PERMISSION_GRANTED
//        ) {
//
//        }

        Log.i(LOG_TAG, "Bik talak")
            val moshi = Moshi.Builder().build()
            val listType = Types.newParameterizedType(List::class.java, NewsApiJSON::class.java)
            val adapter: JsonAdapter<NewsApiJSON> = moshi.adapter(listType)
//        val s = adapter.
            val json = adapter.toJson((newsData))
            saveTextToFile(app, json)

    }

    fun saveTextToFile(app: Application, json: String?) {
        val file = File(app.cacheDir, "news.json")
        Log.i(LOG_TAG, "Bik talak 2")
        file.writeText(json ?: "", Charsets.UTF_8)
    }

    fun readTextFile(app: Application): String? {
        val file = File(app.cacheDir, "news.json")
        return if(file.exists()){
            file.readText()
        } else null
    }

    private fun readDataFromCache(): List<NewsApiJSON> {
        val json = readTextFile(app)
        if (json == null) {
            Log.i(LOG_TAG, "There is no file!")
            return emptyList()
        }
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, NewsApiJSON::class.java)
        val adapter: JsonAdapter<List<NewsApiJSON>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }

}