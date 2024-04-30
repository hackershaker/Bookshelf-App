package com.example.bookshelf.ui.screen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.MyApplication
import com.example.bookshelf.ui.BookRepository
import com.example.bookshelf.ui.state.BookUiState
import com.example.bookshelf.ui.state.SearchUiState
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private val TAG = "bookshelf.LibraryViewModel"

class LibraryViewModel(
    val repository: BookRepository, private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var _bookUiState = MutableStateFlow<BookUiState>(BookUiState.Start())
    val bookUiState = _bookUiState.asStateFlow()
    private var _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState())
    val searchUiState = _searchUiState.asStateFlow()

    suspend fun getBooksList(
        searchWord: String,
        maxResult: Int = _searchUiState.value.resultNumber,
        startIndex: Int = _searchUiState.value.searchStartIndex
    ) {
        Log.d(TAG, "구글 북스에 요청...")
        this.updateState(BookUiState.Loading())
        try {
            viewModelScope.launch {
                Log.d(TAG, "viewModelScope 시작.")
                val result = repository.search(searchWord, startIndex, maxResult)
                val bookList =
                    result.items.map { item -> async { repository.getBookInfo(item.id) } }
                        .awaitAll()

                _bookUiState.value = BookUiState.Success(bookList = bookList)
                _searchUiState.value.addList(bookList)

                val _newSearchUiState = SearchUiState(
                    resultNumber = _searchUiState.value.resultNumber,
                    searchStartIndex = _searchUiState.value
                        .searchStartIndex + _searchUiState.value.resultNumber,
                    selectedBookIndex = -1,
                    searchList = _searchUiState.value.searchList
                )
                _searchUiState.value = _newSearchUiState

                Log.d(TAG, "구글 북스에서 응답 가져옴")
                Log.d(TAG, "${_searchUiState.value.searchList.lastOrNull()?.volumeInfo?.title}")
            }
        } catch (e: Exception) {
            Log.d(TAG, "getBooksList() Error Occured : $e")
            this.updateState(BookUiState.Error(e))
        }
    }

    fun updateState(state: BookUiState) {
        this._bookUiState.value = state
    }

    fun checkState(state: BookUiState): Boolean {
        return state::class == this._bookUiState.value.let { it::class }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val myRepository = (this[APPLICATION_KEY] as MyApplication).appContainer.repository
                LibraryViewModel(
                    repository = myRepository, savedStateHandle = savedStateHandle
                )
            }
        }
    }
}