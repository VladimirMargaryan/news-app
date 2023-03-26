package com.mobiledev.news_app.service.dto

import com.mobiledev.news_app.domain.model.News

data class NewsDto(
    val articles: List<ArticleDto>? = null,
    val status: String? = null,
    val totalResults: Int? = null
) {
    fun toNews(): News {
        val articles = articles?.map { it.toArticle() }
        return News(
            article = articles ?: emptyList()
        )
    }
}