package com.arribas.bookshelf.network

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.arribas.bookshelf.AppApplication
import com.arribas.bookshelf.data.BookRepository
import com.arribas.bookshelf.model.Book
import com.arribas.bookshelf.model.ResponseListBook
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookUiState {
    data class Success(val response: ResponseListBook) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}

data class BookUiViewState(
    val isShowingListPage: Boolean = true,
    val book: Book? = null
)

class BookViewModel(
    val bookRepository: BookRepository
): ViewModel() {
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    private val _uiState = MutableStateFlow(BookUiViewState())

    val uiState: StateFlow<BookUiViewState> = _uiState

    var search by mutableStateOf("")
        private set

    init {
        search = "cicero"
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            bookUiState = try {
                val response = bookRepository.getBooks(q = search)

                if(response.items.size > 0){
                    updateCurrentTypeList(response.items.get(0))
                }

                BookUiState.Success(response = response)

            } catch (e: IOException) {
                BookUiState.Error

            } catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }

    fun updateSearch(search: String){
        this.search = search
    }

    fun updateCurrentTypeList(book: Book) {

        _uiState.update {
            it.copy(
                book = book,
                isShowingListPage = true
            )
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }

    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppApplication)
                val bookRepository = application.container.bookRepository

                BookViewModel(bookRepository = bookRepository)
            }
        }
    }
}