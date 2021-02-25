package com.example.printfulsimplelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.printfulsimplelist.adapters.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsapi.org"

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
        makeAPIRequest()
    }

    //simple fade in animation for when the app is done loading
    private fun fadeIn() {
        v_blackScreen.animate().apply {
            alpha(0f)
            duration = 2000
        }.start()
    }

    private fun setUpRecyclerView() {
        rv_recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        rv_recyclerView.adapter = RecyclerAdapter(titlesList, descList, imagesList, linksList)
    }

    //adds the items to our recyclerview
    private fun addToList(title: String, description: String, image: String, link: String) {
        linksList.add(link)
        titlesList.add(title)
        descList.add(description)
        imagesList.add(image)
    }


    private fun makeAPIRequest() {
        progressBar.visibility = View.VISIBLE
        Log.d("MainActivity.Load", "Loading....\n")

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)

        Log.d("MainActivity.Load", "Retrofit\n")
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getNews()
                Log.d("MainActivity.Load", "Trying...\n $response")
                for (article in response.articles) {
                    Log.d("MainActivity", "Result = $article")
                    addToList(article.title, article.description, article.urlToImage, article.url)
                }

                //updates ui when data has been retrieved
                withContext(Dispatchers.Main) {
                    setUpRecyclerView()
                    fadeIn()
                    progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                Log.d("MainActivity", e.toString())
                withContext(Dispatchers.Main) {
                    attemptRequestAgain(seconds)

                }
            }

        }
    }

    private fun attemptRequestAgain(seconds: Long){
        countdownTimer = object: CountDownTimer(seconds*1000, 1000){
            override fun onFinish() {
                makeAPIRequest()
                countdownTimer.cancel()
                tv_noInternetCountDown.visibility = View.GONE
                this@MainActivity.seconds+=3
            }

            override fun onTick(millisUntilFinished: Long) {
                tv_noInternetCountDown.visibility = View.VISIBLE
                tv_noInternetCountDown.text = getString(R.string.cannotRetData) + "${millisUntilFinished/1000}"
                Log.d("MainActivity", "Could not retrieve data. Trying again in ${millisUntilFinished/1000} seconds")
            }

        }
        countdownTimer.start()

    }
}
