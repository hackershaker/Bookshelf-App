package com.example.bookshelf.ui.state

import android.util.Log
import com.example.bookshelf.data.bookinfo.BookDetail


private const val TAG = "SearchUiState"

data class SearchUiState(
    var searchStartIndex: Int = 1,
    var resultNumber: Int = 20,
    var selectedBookIndex: Int = -1,
    var searchList: MutableList<BookDetail> = mutableListOf()
) {
    fun reset() {
        selectedBookIndex = -1
        searchStartIndex = 0
        searchList.clear()
    }

    fun addList(list: List<BookDetail>) {
        searchList.addAll(list)
        searchStartIndex = searchList.size + 1
        Log.d(TAG, "현재 searchlist 길이 : ${searchList.size}")
    }
}