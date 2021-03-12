package com.example.printfulsimplelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.example.printfulsimplelist.ui.main.MainViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://newsapi.org"
private lateinit var viewModel: MainViewModel


class MainActivity : AppCompatActivity() {

    lateinit var countdownTimer: CountDownTimer
    private var seconds = 3L

    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<String>()
    private var linksList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        makeAPIRequest()
        Log.i(LOG_TAG, "Main activity 1")

//        getNewsService()
//        loadData()

    }

    //simple fade in animation for when the app is done loading
//    private fun fadeIn() {
//        v_blackScreen.animate().apply {
//            alpha(0f)
//            duration = 2000
//        }.start()
//    }
//
//    private fun setUpRecyclerView() {
//        rv_recyclerView.layoutManager = LinearLayoutManager(applicationContext)
//        rv_recyclerView.adapter = RecyclerAdapter(titlesList, descList, imagesList, linksList)
//
//
//    }
//
//    //adds the items to our recyclerview
//    private fun addToList(title: String, description: String, image: String, link: String) {
//        linksList.add(link)
//        titlesList.add(title)
//        descList.add(description)
//        imagesList.add(image)
//    }
//
//
//    private fun makeAPIRequest() {
//        progressBar.visibility = View.VISIBLE
//        Log.d("MainActivity.Load", "Loading....\n")
//
//        val api = Retrofit.Builder()
//            .baseUrl(BASE_URL)retrofit.create
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(APIRequest::class.java)
//
//        Log.d("MainActivity.Load", "Retrofit\n")
//
//
//        Log.i(LOG_TAG, "Moshi data: ${viewModel.newsData}")
//
////        Global scope will be running even if parent will stop running.
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val response = api.getNews()
//                Log.d("MainActivity.Load", "Trying...\n $response")
//                for (article in response.articles) {
//                    Log.d("MainActivity", "Result = $article")
//                    addToList(article.title, article.description, article.urlToImage, article.url)
//                }
//
//                //updates ui when data has been retrieved
//                withContext(Dispatchers.Main) {
//                    setUpRecyclerView()
//                    fadeIn()
//                    progressBar.visibility = View.GONE
//                }
//            } catch (e: Exception) {
//                Log.d("MainActivity", e.toString())
//                withContext(Dispatchers.Main) {
//                    attemptRequestAgain(seconds)
//
//                }
//            }
//        }
//    }
//
//    private fun attemptRequestAgain(seconds: Long){
//        countdownTimer = object: CountDownTimer(seconds*1000, 1000){
//            override fun onFinish() {
//                makeAPIRequest()
//                countdownTimer.cancel()
//                tv_noInternetCountDown.visibility = View.GONE
//                this@MainActivity.seconds+=3
//            }
//
//            override fun onTick(millisUntilFinished: Long) {
//                tv_noInternetCountDown.visibility = View.VISIBLE
//                tv_noInternetCountDown.text = getString(R.string.cannotRetData) + "${millisUntilFinished/1000}"
//                Log.d("MainActivity", "Could not retrieve data. Trying again in ${millisUntilFinished/1000} seconds")
//            }
//
//        }
//        countdownTimer.start()
//
//    }

//    suspend fun loadData(){
//        getNewsService()
//
//    }



    fun getNewsService() {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(GLOBAL_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()

        val service = retrofit.create(APIRequest::class.java)
        Log.i(LOG_TAG, "Bik further 3")
        corutines(service)
    }

    fun corutines(service: APIRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            // Do the GET request and get response
            try {
                val response = service.getNews().body()?.articles ?: emptyList()

                val aSuc = response.toString()
                Log.i(LOG_TAG, "First test: Success!<>., \n $aSuc")
            } catch (e: Exception,){
                Log.i(LOG_TAG, "First test fail: $e")
            }

        }
//        finnaly(service)
        }

//        fun finnaly(response: APIRequest){
//            Log.i(LOG_TAG, response.toString())
//            Log.i(LOG_TAG, "Bik further 2")
//            response.
//
//            withContext(Dispatchers.Main) {
//                if (response?.status.isNullOrEmpty()) {
//                    Log.i(LOG_TAG, "Šaaize 1")
//                }
//                val items = response?.articles
//                if (items != null) {
//                    for (i in 0 until items.count()) {
//                        var author  = items[i].author ?: "N/A"
//                        Log.d("Author: ", author)
//
//                        var a = items[i].title ?: "N/A"
//                        Log.d("Title: ", a)
//                    }
//                }
//            }
//        }



}
