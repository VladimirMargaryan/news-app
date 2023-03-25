package com.mobiledev.news_app.presentation

import com.mobiledev.news_app.service.enums.Category

data class NewsState(
    val error: String = "",
    val searchQuery: String = "",
    val categories: List<Category> = Category.values().toList(),
    val selectedCategoryIndex: Int = 0,
    val dropDownExpanded: Boolean = false
)