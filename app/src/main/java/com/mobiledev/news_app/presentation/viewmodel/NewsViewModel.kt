package com.mobiledev.news_app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mobiledev.news_app.domain.repository.NewsRepository
import com.mobiledev.news_app.presentation.NewsState

class NewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _state = MutableLiveData(NewsState())
    val state: LiveData<NewsState> = _state

    init {
        fetchNews()
    }

    fun onValueChange(transform: NewsState.() -> NewsState) {
        _state.value = _state.value?.run(transform)
    }

    fun fetchNews() {
        onValueChange {
            copy(
                articles = repository.getNewsPage().cachedIn(viewModelScope)
            )
        }
    }
}