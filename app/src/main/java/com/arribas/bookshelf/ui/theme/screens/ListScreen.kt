package com.arribas.bookshelf.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.arribas.bookshelf.model.Book
import com.arribas.bookshelf.model.ResponseListBook

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    response: ResponseListBook,
    search: String,
    onSearchChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    onItemClick: (Book) -> Unit,
    modifier: Modifier = Modifier
){
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)) {

        OutlinedTextField(
            value = search,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            colors = TextFieldDefaults.textFieldColors(containerColor = MaterialTheme.colorScheme.surface),
            onValueChange = onSearchChanged,

            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),

            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone(); focusManager.clearFocus() }
            ),

            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )

        Text(
            text = response.totalItems.toString() + " Libros encontrados",
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(top = 4.dp, bottom = 4.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(
                items = response.items,
                key = { book -> book.id }
            ) { book ->
                BookCard(
                    book = book,
                    onItemClick = { onItemClick(book) },
                    modifier = modifier
                )
            }
        }
    }
}