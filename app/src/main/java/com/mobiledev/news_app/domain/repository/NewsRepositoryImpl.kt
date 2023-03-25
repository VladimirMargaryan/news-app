package com.mobiledev.news_app.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mobiledev.news_app.domain.model.Article
import com.mobiledev.news_app.domain.model.News
import com.mobiledev.news_app.service.network.Resource
import com.mobiledev.news_app.service.network.api.NewsApi
import com.mobiledev.news_app.domain.repository.paging.NewsPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val api: NewsApi
) : NewsRepository {

    override suspend fun getNewsList(): Flow<Resource<News>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val response = api.getTopHeadlines()
                emit(Resource.Success(data = response.toNews()))
                emit(Resource.Loading(isLoading = false))
            } catch (e: Exception) {
                emit(Resource.Loading(isLoading = false))
                emit(Resource.Error(message = "${e.message}"))
            }
        }
    }

    override fun getNewsPage(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = NewsApi.DEFAULT_PAGE_SIZE,
            ),
            pagingSourceFactory = {
                NewsPagingSource(api)
            }
        ).flow
    }
}