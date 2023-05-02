package com.mobiledev.news_app.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mobiledev.news_app.domain.model.Article
import com.mobiledev.news_app.domain.repository.paging.NewsPagingSource
import com.mobiledev.news_app.service.api.NewsApi
import com.mobiledev.news_app.service.enums.Category
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val api: NewsApi
) : NewsRepository {

    override fun getNewsPage(query: String?, category: Category?): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = NewsApi.DEFAULT_PAGE_SIZE,
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = api,
                    queryParams = mapOf(
                        Pair(NewsPagingSource.QUERY_PARAM_KEY, query),
                        Pair(NewsPagingSource.CATEGORY_PARAM_KEY, category?.queryValue)
                    )
                )
            }
        ).flow
    }
}