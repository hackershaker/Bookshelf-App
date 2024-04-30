package com.example.bookshelf.ui.state

import com.example.bookshelf.data.bookinfo.BookDetail
import com.example.bookshelf.data.booklist.SearchResult

sealed class BookUiState {
    class Start : BookUiState()
    class Loading : BookUiState()
    data class Success(
        val searchResult: SearchResult? = null,
        val bookList: List<BookDetail>,
    ) : BookUiState()

    data class Error(val e: Exception) : BookUiState()
}