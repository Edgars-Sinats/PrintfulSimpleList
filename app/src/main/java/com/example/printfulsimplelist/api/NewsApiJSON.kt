package com.example.printfulsimplelist.api

import com.squareup.moshi.Json

//@JsonClass(generateAdapter = true)
data class NewsApiJSON(
        @field:Json(name = "status")      val status: String,
        @field:Json(name = "totalResults")val totalResults: Int,
        @field:Json(name = "articles")    val articles: List<Article>
) {

}