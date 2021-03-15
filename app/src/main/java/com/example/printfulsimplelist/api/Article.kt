package com.example.printfulsimplelist.api

import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class Article(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source?,
    val title: String,
    val url: String,
    val urlToImage: String? = null
            ){

}