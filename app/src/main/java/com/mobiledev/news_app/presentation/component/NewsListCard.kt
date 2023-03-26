package com.mobiledev.news_app.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.mobiledev.news_app.domain.model.Article
import com.mobiledev.news_app.ui.theme.Blue
import com.mobiledev.news_app.ui.theme.LightGrey

@Composable
fun NewsListItem(
    article: Article,
    onItemClick: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(170.dp)
        .padding(6.dp)
        .clip(shape = RoundedCornerShape(20.dp))
        .clickable { onItemClick() }
    ) {
        Row(
            modifier = Modifier
                .background(color = LightGrey)
                .padding(5.dp)
        ) {
            SubcomposeAsyncImage(
                model = article.urlToImage,
                contentDescription = "article image",
                contentScale = ContentScale.Crop,
                loading = {
                    Box(
                        modifier = Modifier
                            .size(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Blue)
                    }
                },
                error = {
                    Box(
                        modifier = Modifier
                            .size(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Error,
                            contentDescription = "Error",
                            tint = Blue
                        )
                    }
                },
                modifier = Modifier
                    .width(140.dp)
                    .height(160.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
            )

            Spacer(modifier = Modifier.width(6.dp))

            Column(
                modifier = Modifier
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.h6,
                    fontSize = 20.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = article.description,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(20.dp)
                            .clip(shape = RoundedCornerShape(25.dp))
                            .background(color = Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = article.source,
                            fontSize = 11.sp,
                            modifier = Modifier
                                .background(color = Color.White)
                                .clip(shape = RoundedCornerShape(20.dp))
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier
                            .width(110.dp)
                            .height(20.dp)
                            .clip(shape = RoundedCornerShape(25.dp))
                            .background(color = Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = article.publishedAt,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}