package com.mobiledev.news_app.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobiledev.news_app.presentation.NewsState
import com.mobiledev.news_app.presentation.viewmodel.NewsViewModel
import com.mobiledev.news_app.service.enums.Category
import com.mobiledev.news_app.ui.theme.Blue
import com.mobiledev.news_app.ui.theme.LightGrey

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    viewModel: NewsViewModel,
    state: NewsState
) {
    val categories = Category.values().toList()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Blue)
    ) {

        Text(
            text = "NEWS",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(start = 15.dp, top = 10.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(5.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.surface)
        ) {

            Box(modifier = Modifier
                .fillMaxHeight()
                .width(120.dp)
                .clickable { viewModel.onValueChange { copy(dropDownExpanded = true) } }
                .background(LightGrey),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = categories[state.selectedCategoryIndex].displayName,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .width(85.dp)
                        .align(Alignment.Center),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    imageVector = if (state.dropDownExpanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Expand",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.CenterEnd)
                )
            }

            DropdownMenu(
                expanded = state.dropDownExpanded,
                onDismissRequest = { viewModel.onValueChange { copy(dropDownExpanded = false) } }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                            .height(20.dp),
                        onClick = {
                            viewModel.onValueChange {
                                copy(
                                    selectedCategoryIndex = categories.indexOf(category),
                                    dropDownExpanded = false
                                )
                            }
                            viewModel.fetchNews()
                        }
                    ) {
                        val isSelected = category == categories[state.selectedCategoryIndex]
                        Text(
                            text = category.displayName,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isSelected) Blue else Color.Black,
                            textDecoration = if (isSelected) TextDecoration.Underline else TextDecoration.None,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }

            BasicTextField(
                singleLine = true,
                value = state.searchQuery,
                onValueChange = { viewModel.onValueChange { copy(searchQuery = it) } },
                modifier = Modifier
                    .fillMaxHeight()
                    .onFocusChanged { if (it.isFocused) Color.Black else Color.Unspecified }
                    .background(MaterialTheme.colors.surface),
                maxLines = 1,
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                        ) {
                            if (state.searchQuery.isEmpty())
                                Text(
                                    "Search",
                                    style = LocalTextStyle.current.copy(
                                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
                                        fontSize = 15.sp
                                    ),
                                    modifier = Modifier.padding(start = 0.dp)
                                )
                            innerTextField()
                        }
                        IconButton(onClick = {
                            viewModel.fetchNews()
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "search",
                                modifier = Modifier.size(30.dp),
                                tint = Blue
                            )
                        }
                    }
                }
            )
        }
    }
}