package com.mobiledev.news_app.domain.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobiledev.news_app.domain.model.Article
import com.mobiledev.news_app.service.api.NewsApi

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val queryParams: Map<String, String?>
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val response = newsApi
                .getTopHeadlines(
                    query = queryParams[QUERY_PARAM_KEY],
                    category = queryParams[CATEGORY_PARAM_KEY],
                    page = page,
                    pageSize = params.loadSize
                ).toNews()

            LoadResult.Page(
                data = response.article,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.article.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object PagingConstants {
        const val QUERY_PARAM_KEY = "query"
        const val CATEGORY_PARAM_KEY = "category"
    }
}