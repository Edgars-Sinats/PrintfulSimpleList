package com.example.printfulsimplelist.api

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.core.content.ContextCompat
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
        val data = readDataFromCache()
        if (data.isEmpty()) {
            callDataFromWeb()
        } else {
            newsArticle.value = data
            Log.i(LOG_TAG, "Using local data")
        }
    }
/**

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
        saveDataToCache(response)

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

    private fun saveDataToCache(ArticleData: List<Article>) {
        if (ContextCompat.checkSelfPermission(
                        app,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_GRANTED
        ) {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val listType = Types.newParameterizedType(List::class.java, Article::class.java)
            val adapter: JsonAdapter<List<Article>> = moshi.adapter(listType)
            val json = adapter.toJson(ArticleData)
            FileHelper.saveTextToFile(app, json)
        }
    }

    private fun readDataFromCache(): List<Article> {
        val json = FileHelper.readTextFile(app)
        if (json == null) {
            Log.i(LOG_TAG, "There is no file!")
            return emptyList()
        }
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, Article::class.java)
        val adapter: JsonAdapter<List<Article>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }

}