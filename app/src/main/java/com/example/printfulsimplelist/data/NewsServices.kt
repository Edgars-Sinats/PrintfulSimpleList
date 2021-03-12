package com.example.printfulsimplelist.data

import com.example.printfulsimplelist.api.Article
import com.example.printfulsimplelist.api.NewsApiJSON
import com.example.printfulsimplelist.testApiJson.ApiGeneretedJson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NewsServices {
    @GET("/v2/top-headlines?country=lv&apiKey=04a42ee08c094f9580d3cb6e0bd5a43c")
    suspend fun getLvNewsData() : Response<ApiGeneretedJson>

//    @GET (getLvNewsData().body().)
//    suspend fun getArticleList() : Response<List<Article>>

}

