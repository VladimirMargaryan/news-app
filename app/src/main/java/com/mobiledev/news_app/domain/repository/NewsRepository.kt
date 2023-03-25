package com.mobiledev.news_app.domain.repository

import androidx.paging.PagingData
import com.mobiledev.news_app.domain.model.Article
import com.mobiledev.news_app.domain.model.News
import com.mobiledev.news_app.service.network.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsList(): Flow<Resource<News>>

    fun getNewsPage(): Flow<PagingData<Article>>
}