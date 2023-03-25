package com.mobiledev.news_app.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mobiledev.news_app.presentation.component.NewsListItem
import com.mobiledev.news_app.presentation.component.SearchBar
import com.mobiledev.news_app.presentation.viewmodel.NewsViewModel
import com.mobiledev.news_app.ui.theme.Blue
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        SearchBar()
        NewsList()
    }
}

@Composable
fun NewsList(
    viewModel: NewsViewModel = koinViewModel()
) {
    val articles = viewModel.getNewsPage().collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        LazyColumn {
            items(
                items = articles,
                key = { articles.itemSnapshotList.indexOf(it) }
            ) { article ->
                article?.let {
                    NewsListItem(
                        article = article,
                        onItemClick = {
                            // TODO: Navigate to details screen
                        }
                    )
                }
            }

            if (articles.loadState.refresh is LoadState.Error) {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Something went wrong"
                        )
                    }
                }
            } else if (articles.loadState.refresh is LoadState.Loading) {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Loading"
                        )

                        CircularProgressIndicator(color = Blue)
                    }
                }
            }

            if (articles.loadState.append is LoadState.Loading) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator(color = Blue)
                    }
                }
            }
        }
    }
}