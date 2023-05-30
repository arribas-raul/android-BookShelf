package com.arribas.bookshelf.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.arribas.bookshelf.R
import com.arribas.bookshelf.model.Book
import com.arribas.myvideogames.util.WindowContentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBookshelfApp(
    book: Book? = null,
    isShowingListPage: Boolean,
    onBackButtonClick: () -> Unit,
    contentType: WindowContentType,
    modifier: Modifier = Modifier
){
    val title = if (isShowingListPage) stringResource(R.string.app_name) else book?.volumeInfo?.title

    TopAppBar(
        title = {
            Text(text = title.toString())
        },

        navigationIcon =
        if (!isShowingListPage && contentType != WindowContentType.LIST_AND_DETAIL) {
            {
                IconButton(onClick = { onBackButtonClick() } ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            }

        } else {
            { Box() {} }
        },

        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),

        modifier = modifier
    )
}