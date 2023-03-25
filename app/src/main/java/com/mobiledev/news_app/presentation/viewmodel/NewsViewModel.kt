package com.mobiledev.news_app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobiledev.news_app.domain.model.Article
import com.mobiledev.news_app.domain.repository.NewsRepository
import com.mobiledev.news_app.presentation.NewsState
import kotlinx.coroutines.flow.Flow

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _state = MutableLiveData(NewsState())
    val state: LiveData<NewsState> = _state

    fun onValueChange(transform: NewsState.() -> NewsState) {
        _state.value = state.value?.run(transform)
    }

    fun getNewsPage(): Flow<PagingData<Article>> = repository.getNewsPage().cachedIn(viewModelScope)
}