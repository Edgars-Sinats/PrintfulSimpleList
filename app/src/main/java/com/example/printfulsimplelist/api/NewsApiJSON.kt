package com.example.printfulsimplelist.api

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class NewsApiJSON(
        @field:Json(name = "articles")    val articles: List<Article>,
        @field:Json(name = "status")      val status: String,
        @field:Json(name = "totalResults")val totalResults: Int
) {

}