package com.arribas.bookshelf.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arribas.bookshelf.model.Book

@Composable
fun DetailScreen(
    book: Book?,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()),

        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = book?.volumeInfo?.title.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
        )

        if(book?.volumeInfo?.subtitle != null) {
            Text(
                text = book?.volumeInfo?.subtitle.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 18.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
            )
        }

        Divider(
            color = Color.DarkGray,
            thickness = 0.5.dp,
            modifier = Modifier.padding(start = 10.dp, top = 2.dp, bottom = 20.dp, end = 10.dp))

        BookCard(
            book = book,
            onItemClick = {},
            modifier = modifier
        )

        Text(
            text = book?.volumeInfo?.description.toString(),
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp, start = 40.dp, end = 40.dp)
        )

        Text(
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.background(Color.Blue),
            text = book?.volumeInfo?.authors.toString()
        )
    }
}