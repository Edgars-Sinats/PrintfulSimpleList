package com.example.printfulsimplelist.api

import com.example.printfulsimplelist.api.NewsApiJSON
import retrofit2.Response
import retrofit2.http.GET

interface APIRequest {

    /**
     Searching after documentation in
     https://newsapi.org/docs/endpoints

     Optional, example:
     https://newsapi.org/v2/everything?q=bitcoin&apiKey=04a42ee08c094f9580d3cb6e0bd5a43c
     */

    @GET("/v2/top-headlines?country=lv&apiKey=04a42ee08c094f9580d3cb6e0bd5a43c")
    suspend fun getNews() : Response<NewsApiJSON>
}