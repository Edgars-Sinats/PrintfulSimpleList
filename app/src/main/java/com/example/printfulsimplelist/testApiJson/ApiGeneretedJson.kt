package com.example.printfulsimplelist.testApiJson

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// @Serializable
@JsonClass(generateAdapter = true)
data class ApiGeneretedJson(

        @field:Json(name = "status")
        val status: String? = "okk",

        @field:Json(name = "totalResults")
        val totalResults: Int? = 5,

        @field:Json(name = "articles")
        val articles: List<Article>?
)