package com.example.printfulsimplelist.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Source(
    val id: Char?,
    val name: String?
)