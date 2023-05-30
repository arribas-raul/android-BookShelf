package com.arribas.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arribas.bookshelf.model.ResponseListBook
import com.arribas.bookshelf.network.BookUiState
import com.arribas.bookshelf.network.BookViewModel
import com.arribas.bookshelf.ui.theme.BookshelfTheme
import com.arribas.bookshelf.ui.theme.screens.Appscreen
import com.arribas.bookshelf.ui.theme.screens.HomeScreen

class MainActivity : ComponentActivity() {
    private val viewModel: BookViewModel by viewModels { BookViewModel.Factory }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookshelfTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)

                    Appscreen(
                        viewModel = viewModel,
                        windowSize = windowSize.widthSizeClass
                    )
                }
            }
        }
    }
}


