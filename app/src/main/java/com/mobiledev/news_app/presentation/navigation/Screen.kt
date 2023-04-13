package com.mobiledev.news_app.presentation.navigation

sealed class Screen(val route: String) {
    object NewsScreen : Screen("news")
    object NewsDetailsScreen : Screen("details")

    fun withIndex(articleIndex: Int): String {
        return buildString {
            append(route)
                .append("/")
                .append(articleIndex.toString())
        }
    }
}
