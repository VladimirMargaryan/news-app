package com.mobiledev.news_app.service.dimodules

import com.mobiledev.news_app.domain.repository.NewsRepository
import com.mobiledev.news_app.domain.repository.NewsRepositoryImpl
import com.mobiledev.news_app.presentation.viewmodel.NewsViewModel
import com.mobiledev.news_app.service.api.NewsApi
import com.mobiledev.news_app.service.api.NewsApi.Companion.BASE_URL
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val newsModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }

    viewModelOf(::NewsViewModel)
}