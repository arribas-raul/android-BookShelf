package com.arribas.bookshelf.ui.theme.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arribas.bookshelf.network.BookUiState
import com.arribas.bookshelf.network.BookViewModel
import com.arribas.myvideogames.util.WindowContentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appscreen(
    viewModel: BookViewModel,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier) {

    val uiState by viewModel.uiState.collectAsState()
    val contentType: WindowContentType

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            contentType = WindowContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            contentType = WindowContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            contentType = WindowContentType.LIST_AND_DETAIL
        }
        else -> {
            contentType = WindowContentType.LIST_ONLY
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopBookshelfApp(
                book = uiState.book,
                isShowingListPage = uiState.isShowingListPage,
                contentType =  contentType,
                onBackButtonClick = { viewModel.navigateToListPage() }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                viewModel = viewModel,
                contentType = contentType,
                modifier = modifier
            )
        }
    }

}

@Composable
fun HomeScreen(
    viewModel: BookViewModel,
    contentType: WindowContentType,
    modifier: Modifier = Modifier){

    val bookUiState = viewModel.bookUiState
    val uiState by viewModel.uiState.collectAsState()

    Row(modifier = modifier) {

        if (contentType == WindowContentType.LIST_AND_DETAIL) {
            when (bookUiState) {
                is BookUiState.Loading -> {
                    LoadingScreen(
                        modifier = modifier
                            .weight(1f)
                    )

                    LoadingScreen(
                        modifier = modifier
                            .weight(1f)
                    )
                }

                is BookUiState.Success -> {
                    ListScreen(
                        bookUiState.response,
                        search = viewModel.search,
                        onSearchChanged = { viewModel.updateSearch(it) },
                        onKeyboardDone = { viewModel.getBooks() },
                        onItemClick = {
                            viewModel.updateCurrentTypeList(it)
                            viewModel.navigateToDetailPage()
                        },
                        modifier = modifier
                            .weight(1f)
                    )

                    DetailScreen(
                        book = uiState.book,
                        modifier = modifier
                            .weight(1f)
                    )

                }

                is BookUiState.Error -> {
                    ErrorScreen(
                        modifier = modifier
                            .weight(1f)
                    )

                    ErrorScreen(
                        modifier = modifier
                            .weight(1f)
                    )
                }

                else -> {}
            }

        } else {
            if (uiState.isShowingListPage) {
                when (bookUiState) {
                    is BookUiState.Loading -> LoadingScreen(modifier)

                    is BookUiState.Success -> ListScreen(
                        bookUiState.response,
                        search = viewModel.search,
                        onSearchChanged = { viewModel.updateSearch(it) },
                        onKeyboardDone = { viewModel.getBooks() },
                        onItemClick = {
                            viewModel.updateCurrentTypeList(it)
                            viewModel.navigateToDetailPage()
                        },
                        modifier = modifier
                    )

                    is BookUiState.Error -> ErrorScreen(modifier)
                    else -> {}
                }

            } else {
                DetailScreen(book = uiState.book!!, modifier = modifier)
            }
        }
    }
}











