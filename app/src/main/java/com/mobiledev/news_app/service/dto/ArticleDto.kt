package com.mobiledev.news_app.service.dto

import android.annotation.SuppressLint
import com.mobiledev.news_app.domain.model.Article
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

data class ArticleDto(
    val source: SourceDto? = null,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
) {

    @SuppressLint("NewApi")
    fun toArticle(): Article {
        return Article(
            source = source?.name ?: "",
            author = author ?: "",
            publishedAt = DateTimeFormatter
                .ofPattern("dd-MM-yyyy HH:mm")
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault())
                .format(Instant.parse(publishedAt)) ?: "",
            content = content ?: "",
            urlToImage = urlToImage ?: "",
            url = url ?: "",
            title = title ?: "",
            description = description ?: ""
        )
    }
}