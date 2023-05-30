package com.arribas.bookshelf.ui.theme.screens

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arribas.bookshelf.R
import com.arribas.bookshelf.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookCard(
    book: Book?,
    onItemClick: (Book?) -> Unit,
    modifier: Modifier = Modifier
){

    val img = book?.volumeInfo?.imageLinks?.thumbnail.toString().replace("http","https")

    Card(
        elevation = CardDefaults.cardElevation(),
        onClick = { onItemClick(book) },
        modifier = modifier
            .padding(4.dp)
            .aspectRatio(0.7f)

    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(img)
                .crossfade(true)
                .build(),

            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),

            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = "Book",
            contentScale = ContentScale.FillBounds
        )
    }
}