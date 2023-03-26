package com.mobiledev.news_app.domain.repository

import androidx.paging.PagingData
import com.mobiledev.news_app.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNewsPage(): Flow<PagingData<Article>>
}