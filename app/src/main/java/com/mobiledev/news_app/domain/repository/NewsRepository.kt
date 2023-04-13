package com.mobiledev.news_app.domain.repository

import androidx.paging.PagingData
import com.mobiledev.news_app.domain.model.Article
import com.mobiledev.news_app.service.enums.Category
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNewsPage(query: String? = null, category: Category?): Flow<PagingData<Article>>
}