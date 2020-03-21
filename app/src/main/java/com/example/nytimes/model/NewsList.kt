package com.example.nytimes.model

import java.io.Serializable

data class NewsList(
    val copyright: String,
    val last_updated: String,
    val num_results: Int,
    val results: List<Result>,
    val section: String,
    val status: String
)