package com.mobiledev.news_app.domain.model

data class Article(
    val source: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)