package com.mobiledev.news_app.presentation

import androidx.paging.PagingData
import com.mobiledev.news_app.domain.model.Article
import com.mobiledev.news_app.service.enums.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class NewsState(
    val error: String = "",
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>> = flowOf(PagingData.empty()),
    val categories: List<Category> = Category.values().toList(),
    val selectedCategoryIndex: Int = 0,
    val dropDownExpanded: Boolean = false,
    val isSwipeRefreshing: Boolean = false
) {
    fun getSelectedCategory(): Category {
        return categories[selectedCategoryIndex]
    }
}