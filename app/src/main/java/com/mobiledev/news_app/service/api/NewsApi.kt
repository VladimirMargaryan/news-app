package com.mobiledev.news_app.service.api

import com.mobiledev.news_app.service.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("$VERSION_V2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = COUNTRY,
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsDto

    companion object {
        const val BASE_URL = "https://newsapi.org"
        const val VERSION_V2 = "v2"
        const val COUNTRY = "us"
        const val API_KEY = "48129b39290446ff8ba9173e74701830"

        const val DEFAULT_PAGE = 0
        const val DEFAULT_PAGE_SIZE = 10
    }
}