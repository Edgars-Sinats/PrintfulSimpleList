package com.example.printfulsimplelist.api

data class NewsApiJSON(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)