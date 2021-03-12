package com.example.printfulsimplelist.testApiJson

data class Article(
    val author: Any? = null,
    val content: String,
    val description: String? = null,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String? = null
)