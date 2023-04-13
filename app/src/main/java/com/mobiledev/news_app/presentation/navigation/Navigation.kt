package com.mobiledev.news_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.mobiledev.news_app.presentation.NewsState
import com.mobiledev.news_app.presentation.screen.NewsDetailScreen
import com.mobiledev.news_app.presentation.screen.NewsScreen
import com.mobiledev.news_app.presentation.viewmodel.NewsViewModel


@Composable
fun Navigation(
    state: NewsState,
    viewModel: NewsViewModel
) {
    val navController = rememberNavController()
    val articles = state.articles.collectAsLazyPagingItems()

    NavHost(
        navController = navController,
        startDestination = Screen.NewsScreen.route
    ) {
        composable(Screen.NewsScreen.route) {
            NewsScreen(
                navController = navController,
                state = state,
                articles = articles,
                viewModel = viewModel
            )
        }

        composable(
            Screen.NewsDetailsScreen.route + "/{articleIndex}",
            arguments = listOf(navArgument("articleIndex") { type = NavType.IntType })
        ) { entry ->
            entry.arguments?.getInt("articleIndex")?.let { index ->
                articles[index]?.let {
                    NewsDetailScreen(
                        navController = navController,
                        article = it
                    )
                }
            }
        }
    }
}