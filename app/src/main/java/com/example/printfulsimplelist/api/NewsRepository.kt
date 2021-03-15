package com.example.printfulsimplelist.api

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.printfulsimplelist.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

class NewsRepository (val app: Application){

    val newsArticle = MutableLiveData<List<Article>>()

    private val listTypeArticle = Types.newParameterizedType(
            List::class.java, Article::class.java
    )

    init {
        //Will call data locally from cache
        callDataFromWeb()
    }
/**
//        val data = readDataFromCache()
//        if (data.isEmpty()) {
//        } else {
//            newsData.value = data
//            Log.i(LOG_TAG, "Using local data")
//        }
*/


    fun callDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()

//            getTextFromAssets()
        }
    }

    fun getTextFromAssets(){
        val data = FileHelper.getTextFromAssets(app, "apiLocal.json")
        fun parseText(text: String) {
            val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            val adapter: JsonAdapter<Article> =
                    moshi.adapter(listTypeArticle)
//            newsArticle.value = adapter.fromJson(data)
            val newsD = adapter.fromJson(data)
//            Log.i(LOG_TAG,
//                        "${newsD!!.articles} (\$${newsD.status})")
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
        val response = service.getNews().body()?.articles ?: emptyList()
        newsArticle.postValue(response)

    }




//    ===========TODO================
    //Next step... save data locally

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